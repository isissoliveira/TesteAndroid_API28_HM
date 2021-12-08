package hotmart.android.recyclerapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import hotmart.android.recyclerapplication.adapter.LocatioAdapter
import hotmart.android.recyclerapplication.model.ListLocations
import hotmart.android.recyclerapplication.model.LocationImage
import hotmart.android.recyclerapplication.service.FireStoreService
import hotmart.android.recyclerapplication.service.SingleListLocationsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var meuGridLayoutManager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var minhasImagens: ArrayList<LocationImage>

    val meuContexto = this
    var cont= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        minhasImagens = ArrayList<LocationImage>()

        // FUNÇÃO PARA BUSCAR AS IMAGENS NO FIRESTORE
        buscaImagens()

        meuGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        //trataRecyclerView()
        //FireStoreService().cadastraImagens()
    }

    private fun trataRecyclerView() {

        SingleListLocationsApi.RETROFIT_INTERFACE.getListLocations().enqueue( object : Callback<ListLocations>{

            override fun onResponse( call: Call<ListLocations>, response: Response<ListLocations> ) {

                if (response.isSuccessful && response.body() != null) {
                    Log.i(TAG, "===============SUCESSO=================" )

                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
                        myAdapter       = LocatioAdapter( response.body()?.listLocations!!, minhasImagens )
                        layoutManager   = meuGridLayoutManager
                        adapter         = myAdapter
                    }
                    recyclerView.setHasFixedSize(true)

                }
            }
            override fun onFailure(call: Call<ListLocations>, t: Throwable) {
                Toast.makeText( meuContexto, getString(R.string.falha_conexao) , Toast.LENGTH_LONG ).show()
            }

        })
    } //FIM preencheRecyclerView


    private fun buscaImagens( ) {
        val imagensCadastradas = FireStoreService.meuFireStore.instancia
            .collection("location_images")
            .whereEqualTo("principal", true)

        imagensCadastradas.get()
            .addOnSuccessListener { documents ->
                if (documents != null && cont == 0) {
                    for( document in documents) {
                        minhasImagens.add( document.toObject(LocationImage::class.java))
                        cont++
                    }
                }
                trataRecyclerView()
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, getString(R.string.falha_imagens), exception)
                Toast.makeText(meuContexto, getString(R.string.falha_imagens), Toast.LENGTH_LONG).show()
                trataRecyclerView()
            }

    }


    /*
    private fun buscaImagensFirestore() {
        FireStoreService.meuFireStore.instancia
            .collection("location_images")
            .whereEqualTo("principal", true)
            .orderBy("location_id")
            .addSnapshotListener( object : EventListener<QuerySnapshot>{
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
    */


}