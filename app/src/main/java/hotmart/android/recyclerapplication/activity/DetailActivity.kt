package hotmart.android.recyclerapplication.activity

import android.content.ContentValues
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.squareup.picasso.Picasso
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.adapter.FotoAdapter
import hotmart.android.recyclerapplication.model.LocationDetail
import hotmart.android.recyclerapplication.model.LocationImage
import hotmart.android.recyclerapplication.service.FireStoreService
import hotmart.android.recyclerapplication.service.SingleLocationDetailApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {
    private lateinit var minhasImagens: ArrayList<LocationImage>
    var meuContexto = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var locationId = this.intent.extras?.getInt("location_id").toString()

        Log.i(ContentValues.TAG, "===============Location ID é $locationId=================" )

        minhasImagens = ArrayList<LocationImage>()

        buscaImagensFirestore( locationId)

        preencheTela( locationId)

        acaoVoltar()
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
                    var textViewSobreConteudo : TextView    = findViewById( R.id.id_textViewSobreConteudo)
                    var textViewSchedule: TextView          = findViewById( R.id.id_textViewSchedule)
                    var textViewPhone   : TextView          = findViewById( R.id.id_textViewPhone)
                    var textViewAddress : TextView          = findViewById( R.id.id_textViewAddress)
                    var imgtoolbarDetail: ImageView         = findViewById(R.id.id_imageViewToolBar)

                    // SE locationDetail não for null executamos o trecho abaixo
                    locationDetail?.let{

                        // PREENCHE O RECYCLER VIEW DOS ITENNS DO BLOCO "FOTOS"
                        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_fotos).apply {
                            var fotos       = minhasImagens.filter { img -> !img.principal }
                            var myAdapter   = FotoAdapter( fotos)
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

                        minhasImagens?.let {
                            val imagens : List<LocationImage>? = minhasImagens?.filter { img -> img.principal }

                            imagens?.let {
                                if(imagens.isNotEmpty()){
                                    Picasso.with( meuContexto)
                                        .load(imagens.get(0).storage )
                                        .into(imgtoolbarDetail)
                                }
                            }
                        }

                    } // FIM Let

                }

            }

            override fun onFailure(call: Call<LocationDetail>, t: Throwable) {
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
        var indiceDiaInicial    : Int = 0
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
            if( sched.open.equals(textoHoraOpenAtual) && sched.close.equals(textoHoraCloseAtual)  ){
                textoIntervDiaFim = nomesDiasschedules[i]
                diasSequenciais ++
            }
            // SE PRÓXIMO HORÁRIO É DIFERENTE DO INTERVALO ANTERIOR ADICIONAMOS O INTERVALO ANTERIOR EM "textoSched"
            else {
                if( textoIntervDiaFim.isEmpty() ) {
                    textoSched += "$textoIntervDiaIni : $textoIntervHorarios \n"
                }
                else{
                    textoSched += "$textoIntervDiaIni ${ if(diasSequenciais > 1)  stringA else stringE } $textoIntervDiaFim : $textoIntervHorarios \n"
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
        if( textoIntervDiaFim.isEmpty() ) {
            textoSched += "$textoIntervDiaIni : $textoIntervHorarios "
        }
        else{
            textoSched += "$textoIntervDiaIni $stringA $textoIntervDiaFim : $textoIntervHorarios "
        }

        return textoSched

    }

    private fun buscaImagensFirestore( locId : String) {
        FireStoreService.meuFireStore.instancia
            .collection("location_images")
            .whereEqualTo("location_id", locId)
            .addSnapshotListener( object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                    if(error != null){
                        Log.e( "Firestore error ", error.message.toString() )
                        return
                    }
                    for ( dc in value?.documentChanges!!){
                        if( dc.type == DocumentChange.Type.ADDED){

                            // ADICIONAMOS AS IMAGENS OBTIDAS NO FIRESTORE NO ARRAYLIST "minhasImgens"
                            minhasImagens.add( dc.document.toObject(LocationImage::class.java))
                        }
                    }
                }
            })

    }// FIM buscaImagensFirestore

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
