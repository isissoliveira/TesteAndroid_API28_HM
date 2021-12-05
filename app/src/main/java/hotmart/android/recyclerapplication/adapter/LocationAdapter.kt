package hotmart.android.recyclerapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.data.Datasource
import hotmart.android.recyclerapplication.model.Afirmacao
import hotmart.android.recyclerapplication.model.Location

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

    class LocationViewHolder( private val view : View, private val myContext : Context) : RecyclerView.ViewHolder(view){

        fun bind( location : Location) {
            val textViewName: TextView = view.findViewById(R.id.id_textViewName)
            val textViewType: TextView = view.findViewById(R.id.id_textViewType)
            val imageView: ImageView = view.findViewById(R.id.id_imageView)
            val ratingBar: RatingBar = view.findViewById(R.id.id_ratingBarReview)
            var textViewReview : TextView = view.findViewById(R.id.id_textViewReview)


            textViewName.text = location.name
            textViewType.text = location.type
            ratingBar.rating = location.review
            textViewReview.text = location.review.toString()

            val arrayImg = Datasource().buscaAfirmacoes()
            try{
                val afirmacao : Afirmacao = arrayImg[location.id]
                imageView.setImageResource(afirmacao.imageResourceId)
            }catch( e: Exception){
                Toast.makeText( myContext, "Não foram encontradas imagens para todos os itens", Toast.LENGTH_LONG)
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

        adapterLayout.setOnClickListener{
            val intent = Intent(parent.context, DetailActivity::class.java)
            parent.context.startActivity(intent)
        }

        return LocationViewHolder(adapterLayout, parent.context)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)

    }

    override fun getItemCount() = dataset.size

}



