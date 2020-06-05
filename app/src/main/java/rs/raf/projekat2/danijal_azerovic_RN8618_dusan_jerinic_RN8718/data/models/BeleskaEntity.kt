package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beleske")
data class BeleskaEntity(
    @PrimaryKey(autoGenerate = true) val id: String,
    val title: String,
    val text: String,
    val archived: Boolean
)