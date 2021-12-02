package hotmart.android.recyclerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import hotmart.android.recyclerapplication.adapter.ItemAdapter
import hotmart.android.recyclerapplication.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meuDataset = Datasource().buscaAfirmacoes()
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)


        val meuGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = meuGridLayoutManager

       // recyclerView.layoutManager = GridLayoutManager( this, 2)
       // recyclerView.layoutManager = FlexboxLayoutManager(this, FlexDirection.ROW, )

        recyclerView.adapter = ItemAdapter(this, meuDataset)

        recyclerView.setHasFixedSize(true)
    }
}