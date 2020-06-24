package br.com.homartapp.util

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.homartapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        inline fun <reified T> fromJson(json: String?): T {
            return Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)
        }

        inline fun <reified T> toJson(obj: T): String {
            return Gson().toJson(obj)
        }
    }

}

fun ImageView.loadImage(uri: String?, transform: Boolean = false) {
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    var options = RequestOptions()
        .placeholder(circularProgressDrawable)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

        .error(R.mipmap.ic_launcher)
    if(!transform){
        options = options.dontTransform()
    }
    Glide.with(this.context)
        .load(uri)
        .apply(options)
        .into(this)
}

@Throws(ParseException::class)
fun String.parseDayOfWeek(locale: Locale = Locale.getDefault()): String {
    val dayFormat = SimpleDateFormat("E", locale)
    val date: Date = dayFormat.parse(this)
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
}

@Throws(ParseException::class)
fun String.parseDayOfWeekInt(locale: Locale = Locale.getDefault()): Int {
    val dayFormat = SimpleDateFormat("E", locale)
    val date: Date = dayFormat.parse(this)
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.DAY_OF_WEEK)
}

fun String.isSequence(): Boolean {
    return "1234567890".contains(this)
}

