package hotmart.android.recyclerapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.model.LocationImage

class FotoAdapter (private val dataset: List<LocationImage> ) : RecyclerView.Adapter< FotoAdapter.FotoViewHolder>() {

    class FotoViewHolder( private val view : View ) : RecyclerView.ViewHolder(view){

        fun bind( locationImage : LocationImage ) {

            val imageView: ImageView = view.findViewById(R.id.id_imageView_foto)

            locationImage?.let {
                Picasso.with(view.context)
                     .load(locationImage.storage )
                     .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_foto_item, parent, false)

        return FotoViewHolder(adapterLayout )
    }

    override fun onBindViewHolder(holder: FotoViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind( item )

    }

    override fun getItemCount() = dataset.size


}



