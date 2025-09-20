package rachman.forniandi.aerospaceflightnews.util

import android.animation.ObjectAnimator
import android.annotation.SuppressLint

import android.view.View
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


fun View.animateLoadingProcessData(isVisible: Boolean, duration: Long = 300) {
    ObjectAnimator
        .ofFloat(this, View.ALPHA, if (isVisible) 1f else 0f)
        .setDuration(duration)
        .start()
}