package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "beleske")
data class BeleskaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val text: String,
    val archived: Boolean = false,
    val kreirano: Date = Date()
)