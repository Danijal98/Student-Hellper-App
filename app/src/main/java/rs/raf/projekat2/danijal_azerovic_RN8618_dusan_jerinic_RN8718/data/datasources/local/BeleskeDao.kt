package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity

@Dao
abstract class BeleskeDao {

    @Query("SELECT * FROM beleske")
    abstract fun getAll(): Observable<BeleskaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(beleskaEntity: BeleskaEntity): Completable

    @Delete
    abstract fun delete(beleskaEntity: BeleskaEntity)

    @Update
    abstract fun update(beleskaEntity: BeleskaEntity)

}