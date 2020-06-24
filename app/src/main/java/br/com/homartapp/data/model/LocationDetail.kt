package br.com.homartapp.data.model

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import br.com.homartapp.util.loadImage

class LocationDetail(
    val name: String,
    val review: Double,
    val type: String,
    val id: Int,
    val about: String,
    var schedule: Schedule,
    val phone: String,
    val adress: String,
    val image: String = "https://picsum.photos/210/300.jpg"
)
{
    var totalReviews: Int = 0

    companion object {

    @JvmStatic
    @BindingAdapter("imageDetailUrl")
    fun loadImage(
        view: ImageView,
        url: String
    ) { // This methods should not have any return type, = declaration would make it return that object declaration.
        view.loadImage(url)
    }
}
}

class Schedule {
    var sunday: Day?
    var monday: Day?
    var tuesday: Day?
    var wednesday: Day?
    var thursday: Day?
    var friday: Day?
    var saturday: Day?

    constructor(
        sunday: Day? = null,
        monday: Day? = null,
        tuesday: Day? = null,
        wednesday: Day? = null,
        thursday: Day? = null,
        friday: Day? = null,
        saturday: Day? = null
    ) {
        this.sunday = sunday
        this.monday = monday
        this.tuesday = tuesday
        this.wednesday = wednesday
        this.thursday = thursday
        this.friday = friday
        this.saturday = saturday
    }
}

data class Day(
    val open: String?,
    val close: String?
)