package rachman.forniandi.core.utilRemote

import android.animation.ObjectAnimator
import android.content.Context

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import rachman.forniandi.core.R
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

private const val FILE_DATE_FORMAT = "dd MMMM yyyy"

fun getStringDate(date: String?): String {
    return if (date.isNullOrEmpty()) {
        "-"
    } else {
        try {
            val parsedDate = when {
                date.contains(".") -> {
                    ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
                }
                else -> {
                    ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
                }
            }

            val outputFormatter = DateTimeFormatter.ofPattern(FILE_DATE_FORMAT)
            parsedDate.format(outputFormatter)

        } catch (e: DateTimeParseException) {
            e.printStackTrace()
            "-"
        }
    }
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