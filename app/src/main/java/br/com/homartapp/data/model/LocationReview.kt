package br.com.homartapp.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.homartapp.util.loadImage

class LocationReview(
    val foto: String,
    val review: Double,
    val title: String,
    val descrition: String,
    val local: String
) {
    companion object {

        @JvmStatic
        @BindingAdapter("imageReviewUrl")
        fun loadImage(
            view: ImageView,
            url: String
        ) { // This methods should not have any return type, = declaration would make it return that object declaration.
            view.loadImage(url,true)
        }
    }
}