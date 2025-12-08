package rachman.forniandi.core.utilRemote

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import rachman.forniandi.core.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

private const val FILE_DATE_FORMAT = "dd-MMMM-yyyy HH:mm"

@SuppressLint("SimpleDateFormat")
fun getStringDate(date: String?): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val outputDate = SimpleDateFormat(FILE_DATE_FORMAT)
    var d: Date? = null
    try {
        d = dateFormat.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return outputDate.format(d)
}

/*fun HttpException.getErrorMessage(): String {
    val message = response()?.errorBody()?.string().toString()
    return JSONObject(message).getString("message")
}*/

fun ImageView.showImageSliderInto(context: Context, url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(ContextCompat.getDrawable(context, R.color.dark))
        .into(this)
}


fun View.animateLoadingProcessData(isVisible: Boolean, duration: Long = 300) {
    ObjectAnimator
        .ofFloat(this, View.ALPHA, if (isVisible) 1f else 0f)
        .setDuration(duration)
        .start()
}