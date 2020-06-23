package br.com.homartapp.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.homartapp.util.loadImage

class LocationDetail(
    val name: String,
    val review: Double,
    val type: String,
    val id: Int,
    val about: String,
    val schedule: Schedule,
    val phone: String,
    val adress: String,
    val image: String = "https://picsum.photos/210/300.jpg"
)
 class Schedule {
    var sunday: Day? = null
    var monday: Day? = null
    var tuesday: Day? = null
    var wednesday: Day? = null
    var thursday: Day? = null
    var friday: Day? = null
    var saturday: Day? = null


}

data class Day(
    val open: String,
    val close: String
)