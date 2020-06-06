package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.RasporedEntity

@Dao
abstract class RasporedDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<RasporedEntity>): Completable

    @Query("SELECT * FROM raspored")
    abstract fun getAll(): Observable<List<RasporedEntity>>

    @Query("SELECT * FROM raspored WHERE grupe LIKE '%' || :grupa || '%' AND dan LIKE '%' || :dan || '%' AND (nastavnik LIKE '%' || :filter || '%' OR predmet LIKE '%' || :filter || '%')")
    abstract fun getAllByFilter(filter: String, grupa: String, dan: String): Observable<List<RasporedEntity>>

    @Query("DELETE FROM raspored")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<RasporedEntity>){
        deleteAll()
        insertAll(entities).blockingAwait()
    }

}