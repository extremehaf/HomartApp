package br.com.homartapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import br.com.homartapp.data.model.Location
import kotlin.random.Random
import br.com.homartapp.databinding.ItemLocationBinding as ItemLocationBinding1


class LocationsRecyclerViewAdapter(var locations: ArrayList<Location>) :
    RecyclerView.Adapter<LocationsViewHolder>() {

    fun updateLocations(newUsers: List<Location>) {
        locations.clear()
        locations.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = LocationsViewHolder(
        ItemLocationBinding1.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = locations.size
    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val location: Location = locations[position]
        holder.bind(location)
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    HomeFragmentDirections.actionNavigationHomeToLocationDetailsActivity(
                        location.id
                    )
                );
        }
    }
}

class LocationsViewHolder(val binding: ItemLocationBinding1) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(location: Location) {
        binding.location = location
        binding.imageUrl = fotoAleatoria()
        binding.executePendingBindings()
    }

    //class LocationsViewHolder(itemView: View) :
//    RecyclerView.ViewHolder(itemView) {
////    private val image: ImageView
////    private val title: TextView
////    private val type: TextView
////    private val rating: TextView
////    private val ratingsBar: AppCompatRatingBar
//
//    //private val review: TextView
//    fun bind(location: Location) {
//        with(itemView) {
//            Glide.with(imageLocation.context).clear(imageLocation)
//            imageLocation.loadImage(fotoAleatoria())
//            title.text = location.name
//            type.text = location.type
//            textRatingValue.text = "%.2f".format(location.review)
//            appCompatRatingBar.numStars = 5
//            appCompatRatingBar.stepSize = 0.1f
//            appCompatRatingBar.rating = location.review.toFloat()
//        }
//
////        image.loadImage(fotoAleatoria())
////        title.text = location.name
////        type.text = location.type
////        rating.text = "%.2f".format(location.review)
////        ratingsBar.numStars = 5
////        ratingsBar.stepSize = 0.1f
////        ratingsBar.rating = location.review.toFloat()
//    }
//
////    init {
////        image = itemView.findViewById(R.id.imageLocation)
////        title = itemView.findViewById(R.id.title)
////        type = itemView.findViewById(R.id.type)
////        rating = itemView.findViewById(R.id.textRatingValue)
////        ratingsBar = itemView.findViewById(R.id.appCompatRatingBar)
////    }
//
//    private fun corAleatoria(): Int {
//        var cor = Random.nextInt(1, 3)
//        when (cor) {
//            1 -> {
//                return R.color.creme;
//            }
//            2 -> {
//                return R.color.light_pink
//            }
//            3 -> {
//                return R.color.duck_egg_blue
//            }
//        }
//        return (cor)
//    }
//
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