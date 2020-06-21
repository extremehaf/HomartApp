package br.com.homartapp.ui.home

import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import br.com.homartapp.R
import br.com.homartapp.data.model.Location
import br.com.homartapp.util.loadImage
import com.bumptech.glide.Glide
import kotlin.random.Random


class LocationsRecyclerViewAdapter(var locations: ArrayList<Location>) :
    RecyclerView.Adapter<LocationsViewHolder>() {

    fun updateLocations(newUsers: List<Location>) {
        locations.clear()
        locations.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = LocationsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)

    )

    override fun getItemCount() = locations.size
    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(locations[position])
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.navigation_locationDetailActivity);
        }
    }

}

class LocationsViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val image: ImageView
    private val title: TextView
    private val type: TextView
    private val rating: TextView
    private val ratingsBar: AppCompatRatingBar

    //private val review: TextView
    fun bind(location: Location) {
        Glide.with(image.context).clear(image)
        image.loadImage(fotoAleatoria())
        title.text = location.name
        type.text = location.type
        rating.text = "%.2f".format(location.review)
        ratingsBar.numStars = 5
        ratingsBar.stepSize = 0.1f
        ratingsBar.rating = location.review.toFloat()
    }

    init {
        image = itemView.findViewById(R.id.imageLocation)
        title = itemView.findViewById(R.id.title)
        type = itemView.findViewById(R.id.type)
        rating = itemView.findViewById(R.id.textRatingValue)
        ratingsBar = itemView.findViewById(R.id.appCompatRatingBar)
    }

    private fun corAleatoria(): Int {
        var cor = Random.nextInt(1, 3)
        when (cor) {
            1 -> {
                return R.color.creme;
            }
            2 -> {
                return R.color.light_pink
            }
            3 -> {
                return R.color.duck_egg_blue
            }
        }
        return (cor)
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
                return "https://picsum.photos/250/600.jpg";
            }
            5 -> {
                return "https://picsum.photos/180/700.jpg";
            }
            6 -> {
                return "https://picsum.photos/300/800.jpg";
            }

        }
        return ""
    }
}