package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.RasporedEntity


@Database(
    entities = [RasporedEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RasporedDataBase : RoomDatabase(){
    abstract fun getRasporedDao(): RasporedDao
}