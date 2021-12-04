package hotmart.android.recyclerapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.data.Datasource
import hotmart.android.recyclerapplication.model.Location
import hotmart.android.recyclerapplication.model.ListLocations

/*
class LocationAdapter (private val dataObject: ListLocations) : RecyclerView.Adapter< LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder( private val view : View) : RecyclerView.ViewHolder(view){

        fun bind( objLocations : ListLocations) {
            val textViewName: TextView = view.findViewById(R.id.id_textViewName)
            val textViewNameType: TextView = view.findViewById(R.id.id_textViewType)
            val imageView: ImageView = view.findViewById(R.id.id_imageView)

            val location = objLocations.listLocations.get(0)

            textViewName.text = location.name
            textViewNameType.text = location.type

            //   Picasso.with(view.context)
            //       .load(location.image)
            //       .into(imageView)
            // imageView.setImageResource(R.drawable.image1)
            // GlideApp.with(view.context).load(propriedade.image).into(imageView)
            //Glide.with(view.context).load(propriedade.image).centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return LocationViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = dataObject
        holder.bind(item)

    }

    override fun getItemCount() = 1

}
*/

class LocatioAdapter (private val dataset: List<Location> ) : RecyclerView.Adapter< LocatioAdapter.LocationViewHolder>() {

    class LocationViewHolder( private val view : View) : RecyclerView.ViewHolder(view){

        fun bind( location : Location) {
            val textViewName: TextView = view.findViewById(R.id.id_textViewName)
            val textViewNameType: TextView = view.findViewById(R.id.id_textViewType)
            val imageView: ImageView = view.findViewById(R.id.id_imageView)

            textViewName.text = location.name
            textViewNameType.text = location.type
            val arrayImg = Datasource().buscaAfirmacoes()
            val lenArrayImg = arrayImg.size

            // SOMENTE EXECUTAREMOS O TRECHO ABAIXO SE O id EXISTIR NO ARRAY DE IMAGENS
            (lenArrayImg == 0).let {
                imageView.setImageResource(Datasource().buscaAfirmacoes().get(location.id).imageResourceId)
            }

            //   Picasso.with(view.context)
            //       .load(location.image)
            //       .into(imageView)
            // imageView.setImageResource(R.drawable.image1)
            // GlideApp.with(view.context).load(propriedade.image).into(imageView)
            //Glide.with(view.context).load(propriedade.image).centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return LocationViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)

    }

    override fun getItemCount() = dataset.size

}



