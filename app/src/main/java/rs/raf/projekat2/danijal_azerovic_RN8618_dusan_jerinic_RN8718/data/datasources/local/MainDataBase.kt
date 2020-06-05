package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.RasporedEntity


@Database(
    entities = [RasporedEntity::class, BeleskaEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters()
abstract class MainDataBase : RoomDatabase(){
    abstract fun getRasporedDao(): RasporedDao
    abstract fun getBeleskeDao(): BeleskeDao
}