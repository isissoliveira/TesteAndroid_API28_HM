package hotmart.android.recyclerapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.model.Propriedade
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.squareup.picasso.Picasso

class ItemAdapter (  private val dataset: List<Propriedade> ) : RecyclerView.Adapter< ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder( private val view : View) : RecyclerView.ViewHolder(view){

        fun bind( propriedade : Propriedade) {
            val textViewName: TextView = view.findViewById(R.id.id_textViewName)
            val textViewNameDesc: TextView = view.findViewById(R.id.id_textViewType)
            val imageView: ImageView = view.findViewById(R.id.id_imageView)

            textViewName.text = propriedade.title
            textViewNameDesc.text = propriedade.description

            Picasso.with(view.context)
                .load(propriedade.image)
                .into(imageView)
           // imageView.setImageResource(R.drawable.image1)
           // GlideApp.with(view.context).load(propriedade.image).into(imageView)
            //Glide.with(view.context).load(propriedade.image).centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
       // val imageView: ImageView = holder.itemView.findViewById(R.id.id_imageView)
       // GlideApp.with(holder.itemView.context).load(item.image).priority(Priority.HIGH).into(imageView)
    }

    override fun getItemCount() = dataset.size

}

@GlideModule
class AppGlideModule : AppGlideModule()



/*class ItemAdapter ( private val context: Context, private val dataset: List<Afirmacao> ) : RecyclerView.Adapter< ItemAdapter.ItemViewHolder>() {

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
*/
