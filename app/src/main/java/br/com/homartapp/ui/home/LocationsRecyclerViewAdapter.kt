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