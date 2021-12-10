package hotmart.android.recyclerapplication.activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.squareup.picasso.Picasso
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.adapter.FotoAdapter
import hotmart.android.recyclerapplication.adapter.ReviewAdapter
import hotmart.android.recyclerapplication.model.LocationDetail
import hotmart.android.recyclerapplication.model.LocationImage
import hotmart.android.recyclerapplication.model.Review
import hotmart.android.recyclerapplication.service.FireStoreService
import hotmart.android.recyclerapplication.service.SingleLocationDetailApi
import hotmart.android.recyclerapplication.service.SingleReviewApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {
    private lateinit var minhasImagens: ArrayList<LocationImage>
    val meuContexto = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val locationId = this.intent.extras?.getInt("location_id").toString()

        Log.i(TAG, "===============Location ID é $locationId=================" )

        minhasImagens = ArrayList()

        montaDados( locationId)

        acaoVoltar()
    }


    private fun montaDados(locId : String) {
        FireStoreService.meuFireStore.instancia
            .collection("location_images")
            .whereEqualTo("location_id", locId)
            .addSnapshotListener( object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                    if(error != null){
                        Log.e( "Firestore error ", error.message.toString() )
                        preencheTela( locId)
                        return
                    }
                    for ( dc in value?.documentChanges!!){
                        if( dc.type == DocumentChange.Type.ADDED){

                            // ADICIONAMOS AS IMAGENS OBTIDAS NO FIRESTORE NO ARRAYLIST "minhasImgens"
                            minhasImagens.add( dc.document.toObject(LocationImage::class.java))
                        }
                    }
                    preencheTela( locId)
                }
            })

        montaReviews( locId )

    }

    private fun preencheTela( id : String) {
        SingleLocationDetailApi.RETROFIT_INTERFACE
            .getLocationDetail( id).enqueue( object : Callback<LocationDetail> {

                override fun onResponse(call: Call<LocationDetail>, response: Response<LocationDetail>) {

                    if (response.isSuccessful) {

                        val locationDetail  : LocationDetail?   = response.body()
                        val textViewTitulo  : TextView          = findViewById( R.id.id_textViewNameDetail)
                        val ratingBar       : RatingBar         = findViewById( R.id.id_ratingBarReviewDetail)
                        val textViewRating  : TextView          = findViewById( R.id.id_textViewReviewDetail)
                        val textViewSobreConteudo : TextView    = findViewById( R.id.id_textViewSobreConteudo)
                        val textViewSchedule: TextView          = findViewById( R.id.id_textViewSchedule)
                        val textViewPhone   : TextView          = findViewById( R.id.id_textViewPhone)
                        val textViewAddress : TextView          = findViewById( R.id.id_textViewAddress)
                        val imgtoolbarDetail: ImageView         = findViewById(R.id.id_imageViewToolBar)

                        // SE locationDetail não for null executamos o trecho abaixo
                        locationDetail?.let{

                            // PREENCHE O RECYCLER VIEW DOS ITENNS DO BLOCO "FOTOS"
                            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_fotos).apply {
                                val fotos       = minhasImagens.filter { img -> !img.principal }
                                val myAdapter   = FotoAdapter( fotos)
                                layoutManager   = LinearLayoutManager(meuContexto, LinearLayoutManager.HORIZONTAL ,false)
                                adapter         = myAdapter
                                isHorizontalScrollBarEnabled = false
                            }
                            recyclerView.setHasFixedSize(true)


                            textViewTitulo.text         = it.name
                            textViewRating.text         = it.review.toString()
                            ratingBar.rating            = it.review
                            textViewSobreConteudo.text  = it.about
                            textViewSchedule.text       = it.meuSchedule?.let { it1 -> textoSchedule(it1) }
                            textViewAddress.text        = it.adress
                            textViewPhone.text          = PhoneNumberUtils.formatNumber( it.phone, "US")

                            val imagens : List<LocationImage> = minhasImagens.filter { img -> img.principal }

                            if(imagens.isNotEmpty()){
                                Picasso.with( meuContexto)
                                    .load(imagens[0].storage )
                                    .into(imgtoolbarDetail)
                            }

                        } // FIM Let
                    }else{
                        Toast.makeText(meuContexto, getString(R.string.falha_reviews), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LocationDetail>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(meuContexto, getString(R.string.falha_conexao), Toast.LENGTH_LONG).show()
                    finish()
                }

            })
    }

    private fun montaReviews(id : String) {
        Log.i( TAG, "===============CHAMA preencheReviews======================")

        SingleReviewApi.RETROFIT_INTERFACE
            .getReviews( id).enqueue( object : Callback<List<Review>> {

                override fun onResponse(call: Call<List<Review>>, response: Response<List<Review>>) {

                    if (response.isSuccessful) {

                        val reviews  : List<Review>?            = response.body()
                        Log.i( TAG, "===============Preenche reviews : ${reviews?.size}======================")
                        // SE reviews não for null executamos o trecho abaixo
                        reviews?.let{

                            // PREENCHE O RECYCLER VIEW DOS ITENNS DO BLOCO "FOTOS"
                            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_reviews).apply {
                                val myAdapter   = ReviewAdapter( it)
                                layoutManager   = LinearLayoutManager(meuContexto, LinearLayoutManager.VERTICAL ,false)
                                adapter         = myAdapter
                                isVerticalScrollBarEnabled = false
                            }
                            recyclerView.setHasFixedSize(false)


                        } // FIM Let
                    }else{
                        Toast.makeText(meuContexto, getString(R.string.falha_reviews), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<Review>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun textoSchedule(objSchedule : LocationDetail.Schedule ) : String{

        var textoSched = ""

        // LISTA DE OBJETOS DaySchedule DO NOSSO OBJETO Schedule
        val diasSchedules = listOf( objSchedule.monday    ,
            objSchedule.tuesday   ,     objSchedule.wednesday   ,
            objSchedule.thursday  ,     objSchedule.friday      ,
            objSchedule.saturday  ,     objSchedule.sunday
        )

        //diasSchedules.forEach {  Log.i(ContentValues.TAG, "=======>${it?.open}" ) }
        var indiceDiaInicial     = 0
        var diaInicial          : LocationDetail.Schedule.DaySchedule? = diasSchedules[0]

        // BUSCA O PRIMEIRO DIA PARA O QUAL HÁ SCHEDULE NO JSON
        for(  (i, dia) in diasSchedules.withIndex() ){
            if( dia != null){
                indiceDiaInicial = i
                diaInicial = dia
                break
            }
        }

        // LISTA DOS NOMES ( ABREVIADOS ) EQUIVALENTES AOS DIAS DA SEMANA DA LISTA "diasSchedules" DE DaySchedule
        val nomesDiasschedules = listOf( getString( R.string.monday)    ,
            getString( R.string.tuesday)        ,     getString( R.string.wednesday)    ,
            getString( R.string.thursday)       ,     getString( R.string.friday)       ,
            getString( R.string.saturday)       ,     getString( R.string.sunday)
        )

        val stringA     : String = getString( R.string.to)    // " a "
        val stringE     : String = getString( R.string.and)   // " e "
        val stringAt    : String = getString( R.string.at)    // " às "

        var textoIntervDiaIni =  nomesDiasschedules[indiceDiaInicial] // getString( R.string.monday)
        var textoIntervDiaFim = ""

        var textoHoraOpenAtual : String?    = diaInicial?.open      // objSchedule?.monday?.open
        var textoHoraCloseAtual: String?    = diaInicial?.close     // objSchedule?.monday?.close
        var textoIntervHorarios             = " $textoHoraOpenAtual $stringAt $textoHoraCloseAtual "

        var diasSequenciais = 0

        // SE APENAS O ÚLTIMO DIA ( sunday) FOI ENCONTRADO, ENTÃO RETORNA
        if( indiceDiaInicial == ( diasSchedules.size - 1 )){
            return "$textoIntervDiaIni $stringA $textoIntervDiaFim : $textoIntervHorarios "
        }

        // O LAÇO É INICIADO NA TERÇA-FEIRA ( it.schedule.tuesday )
        for ( (i, sched) in diasSchedules.withIndex() ){
            if( sched == null || i == indiceDiaInicial ){
                continue
            }

            //BUSCA OS PRÓXIMOS DIAS QUE TEM MESMO HORÁRIO DE open E close
            if( sched.open == textoHoraOpenAtual && sched.close == textoHoraCloseAtual){
                textoIntervDiaFim = nomesDiasschedules[i]
                diasSequenciais ++
            }
            // SE PRÓXIMO HORÁRIO É DIFERENTE DO INTERVALO ANTERIOR ADICIONAMOS O INTERVALO ANTERIOR EM "textoSched"
            else {
                textoSched += if( textoIntervDiaFim.isEmpty() ) {
                    "$textoIntervDiaIni : $textoIntervHorarios \n"
                } else{
                    "$textoIntervDiaIni ${ if(diasSequenciais > 1)  stringA else stringE } $textoIntervDiaFim : $textoIntervHorarios \n"
                }

                // REINICIAMOS AS VARIÁVEIS PARA RECOMEÇAR COM O PRÓXIMO INTERVALO open E close ENCONTRADO
                textoIntervDiaIni   =   nomesDiasschedules[i]
                textoIntervDiaFim   =   ""
                textoHoraOpenAtual  =   sched.open
                textoHoraCloseAtual =   sched.close
                textoIntervHorarios =   " $textoHoraOpenAtual $stringAt $textoHoraCloseAtual "
                diasSequenciais     =   0
            }
        } // FIM For

        // IMPRIME O ÚLTIMO INTERVALO ENCONTRADO
        textoSched += if( textoIntervDiaFim.isEmpty() ) {
            "$textoIntervDiaIni : $textoIntervHorarios "
        } else{
            "$textoIntervDiaIni ${ if(diasSequenciais > 1)  stringA else stringE } $textoIntervDiaFim : $textoIntervHorarios "
        }

        return textoSched

    }

    private fun acaoVoltar(){
        val toolbarDetail : Toolbar = findViewById(R.id.toolbar_detail)

        setSupportActionBar( toolbarDetail )
        val actionBar = supportActionBar
        if( actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
            actionBar.setTitle(R.string.vazio)

        }
        toolbarDetail.setNavigationOnClickListener{ onBackPressed() }
    }
}
