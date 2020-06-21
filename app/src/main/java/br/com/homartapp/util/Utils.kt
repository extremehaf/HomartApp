package br.com.homartapp.util

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import br.com.homartapp.R
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImage(uri: String?) {
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    val options = RequestOptions()
        .placeholder(circularProgressDrawable)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.mipmap.ic_launcher)
    Glide.with(this.context)
        .load(uri)
        .apply(options)
        .into(this)
}