package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.extensions

import android.content.res.Resources

fun Int.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()