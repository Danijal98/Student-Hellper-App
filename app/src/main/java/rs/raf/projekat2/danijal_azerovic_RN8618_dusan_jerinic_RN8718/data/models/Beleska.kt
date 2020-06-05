package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beleska(
    val id: String = "0",
    var title: String,
    var text: String,
    val archived: Boolean = false
): Parcelable