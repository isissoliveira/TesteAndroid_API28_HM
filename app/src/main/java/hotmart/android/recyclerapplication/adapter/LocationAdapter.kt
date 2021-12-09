package hotmart.android.recyclerapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.activity.DetailActivity
import hotmart.android.recyclerapplication.model.Location
import hotmart.android.recyclerapplication.model.LocationImage
import hotmart.android.recyclerapplication.service.FireStoreService

class LocatioAdapter (private val dataset: List<Location>, private val imagens : List<LocationImage>? ) : RecyclerView.Adapter< LocatioAdapter.LocationViewHolder>() {

    class LocationViewHolder( private val view : View ) : RecyclerView.ViewHolder(view){

        fun bind( location : Location , locationImage : LocationImage? ) {
            val textViewName: TextView = view.findViewById(R.id.id_textViewName)
            val textViewType: TextView = view.findViewById(R.id.id_textViewType)
            val imageView: ImageView = view.findViewById(R.id.id_imageView)
            val ratingBar: RatingBar = view.findViewById(R.id.id_ratingBarReview)
            var textViewReview : TextView = view.findViewById(R.id.id_textViewReview)
            var cardView : CardView = view.findViewById(R.id.id_cardView)

            textViewName.text = location.name
            textViewType.text = location.type
            ratingBar.rating = location.review
            textViewReview.text = location.review.toString()

            cardView.setOnClickListener{
                val intent = Intent(cardView.context, DetailActivity::class.java)
                intent.putExtra("location_id", location.id )
                cardView.context.startActivity(intent)
            }

            locationImage?.let {
                Picasso.with(view.context)
                     .load(locationImage.storage )
                     .into(imageView)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return LocationViewHolder(adapterLayout )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = dataset[position]
        val imagem : List<LocationImage>? = imagens?.filter { img -> img.location_id == item.id.toString() }

        holder.bind( item , if(imagem?.size!! > 0) imagem?.get(0) else null)

    }

    override fun getItemCount() = dataset.size

}



