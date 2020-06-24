package br.com.homartapp.ui.locationdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.homartapp.data.model.LocationReview
import br.com.homartapp.databinding.ItemListaBinding
import br.com.homartapp.databinding.ItemReviewBinding
import kotlin.random.Random


class ReviewRecyclerViewAdapter(var locationReview: ArrayList<LocationReview>) :
    RecyclerView.Adapter<ReviewViewHolder>() {

    fun updateReview(newReviews: List<LocationReview>) {
        locationReview.clear()
        locationReview.addAll(newReviews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ReviewViewHolder(
        ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = locationReview.size
    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(locationReview[position])
    }
}

class ReviewViewHolder(val binding: ItemReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(location: LocationReview) {
        binding.review = location
        binding.imageReviewUrl = fotoAleatoria()
        binding.executePendingBindings()
    }

    private fun fotoAleatoria(): String {
        var cor = Random.nextInt(1, 6)
        when (cor) {
            1 -> {
                return "https://picsum.photos/210/300.jpg";
            }
            2 -> {
                return "https://picsum.photos/200/400.jpg";
            }
            3 -> {
                return "https://picsum.photos/290/500.jpg";
            }
            4 -> {
                return "https://picsum.photos/190/220.jpg";
            }
            5 -> {
                return "https://picsum.photos/100/250.jpg";
            }
            6 -> {
                return "https://picsum.photos/210/280.jpg";
            }

        }
        return "https://picsum.photos/150/700.jpg";
    }
}