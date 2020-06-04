package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "raspored")
data class RasporedEntity(
    @PrimaryKey(autoGenerate = true) val id: String,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
)