package hotmart.android.recyclerapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hotmart.android.recyclerapplication.R
import hotmart.android.recyclerapplication.model.LocationImage
import hotmart.android.recyclerapplication.model.Review
import hotmart.android.recyclerapplication.utils.RamdomAvatar

class ReviewAdapter (private val dataset: List<Review> ) :  RecyclerView.Adapter< ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder( private val view : View ) : RecyclerView.ViewHolder(view){

        fun bind( review : Review ) {
            review?.let{
                val imageView: ImageView = view.findViewById(R.id.id_imageViewReviewAvatar)
                val ratingBar: RatingBar = view.findViewById(R.id.id_ratingBarReviewComent)
                val textViewTitulo: TextView = view.findViewById(R.id.id_textViewReviewTitleComent)
                val textViewDescricao: TextView = view.findViewById(R.id.id_textViewReviewComent)
                val textViewAssinatura: TextView = view.findViewById(R.id.id_textViewReviewSign)


                val urlAvatar = if (review.gender == "m") RamdomAvatar().male() else RamdomAvatar().female()
                Picasso.with(view.context)
                    .load(urlAvatar)
                    .into(imageView)

                ratingBar.rating = review.review.toFloat()
                textViewTitulo.text = review.title
                textViewDescricao.text = review.comment

                val assinatura = "${review.name}, ${review.city} - ${review.state} "
                textViewAssinatura.text = assinatura


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_review_item, parent, false)

        return ReviewViewHolder(adapterLayout )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind( item )

    }

    override fun getItemCount() = dataset.size


}



