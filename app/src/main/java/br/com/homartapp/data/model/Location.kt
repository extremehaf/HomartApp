package br.com.homartapp.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.homartapp.util.loadImage
import com.bumptech.glide.Glide

class Location(
    val name: String,
    val review: Double,
    val type: String,
    val id: Int
){
    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) { // This methods should not have any return type, = declaration would make it return that object declaration.
            view.loadImage(url)
        }
    }
}