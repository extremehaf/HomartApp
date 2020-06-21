package br.com.homartapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.RecyclerView
import br.com.homartapp.R
import br.com.homartapp.data.model.Location


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
    }

}

class LocationsViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val title: TextView
    private val type: TextView
    private val rating: TextView
    private val ratingsBar: AppCompatRatingBar

    //private val review: TextView
    fun bind(location: Location) {
        title.text = location.name
        type.text = location.type
        rating.text = "%.2f".format(location.review)
        ratingsBar.numStars = 5
        ratingsBar.stepSize = 0.1f
        ratingsBar.rating = location.review.toFloat()
    }

    init {
        title = itemView.findViewById(R.id.title)
        type = itemView.findViewById(R.id.type)
        rating = itemView.findViewById(R.id.textRatingValue)
        ratingsBar = itemView.findViewById(R.id.appCompatRatingBar)
    }
}