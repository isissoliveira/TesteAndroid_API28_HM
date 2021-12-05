package hotmart.android.recyclerapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import hotmart.android.recyclerapplication.adapter.LocatioAdapter
import hotmart.android.recyclerapplication.model.Location
import hotmart.android.recyclerapplication.model.ListLocations
import hotmart.android.recyclerapplication.service.SingleLocationApi
import hotmart.android.recyclerapplication.service.SingleListLocationsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var meuGridLayoutManager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

    var meuContexto = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        meuGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        preencheRecyclerView()

    }

    private fun preencheRecyclerView() {

        SingleListLocationsApi.RETROFIT_INTERFACE.getListLocations().enqueue( object : Callback<ListLocations>{

            override fun onResponse( call: Call<ListLocations>, response: Response<ListLocations> ) {

                Toast.makeText( meuContexto, response.body().toString(),Toast.LENGTH_LONG)

                if (response.isSuccessful) {
                    Log.i(TAG, "===============SUCESSO=================" )
                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {

                        myAdapter = LocatioAdapter( response.body()?.listLocations!!)
                        layoutManager = meuGridLayoutManager
                        adapter = myAdapter

                    }
                    recyclerView.setHasFixedSize(true)
                }

            }

            override fun onFailure(call: Call<ListLocations>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private fun _preencheRecyclerView() {

        SingleLocationApi.RETROFIT_INTERFACE.getLocations().enqueue( object : Callback<List<Location>>{

            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "===============SUCESSO=================" )
                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {

                        myAdapter = LocatioAdapter( response.body()!!)
                        layoutManager = meuGridLayoutManager
                        adapter = myAdapter

                    }
                    recyclerView.setHasFixedSize(true)
                }
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }



}