package hotmart.android.recyclerapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.model.Afirmacao
import kotlinx.android.synthetic.main.list_item.view.*

class ItemAdapter ( private val context: Context, private val dataset: List<Afirmacao> ) : RecyclerView.Adapter< ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder( private val view : View) : RecyclerView.ViewHolder(view){
        val textView : TextView = view.findViewById(R.id.id_textView)
        val imageView : ImageView = view.findViewById(R.id.id_imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)

    }

    override fun getItemCount() = dataset.size

}