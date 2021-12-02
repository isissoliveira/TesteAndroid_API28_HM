package hotmart.android.recyclerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import hotmart.android.recyclerapplication.adapter.ItemAdapter
import hotmart.android.recyclerapplication.data.Datasource
import kotlinx.android.synthetic.main.activity_main.*
import javax.sql.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meuDataset = Datasource().buscaAfirmacoes()
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = GridLayoutManager( this, 2)
       // recyclerView.layoutManager = FlexboxLayoutManager(this, FlexDirection.ROW, )

        recyclerView.adapter = ItemAdapter(this, meuDataset)

        recyclerView.setHasFixedSize(false)
    }
}