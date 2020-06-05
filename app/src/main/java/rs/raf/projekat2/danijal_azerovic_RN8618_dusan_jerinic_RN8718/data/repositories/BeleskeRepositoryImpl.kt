package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local.BeleskeDao
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity

class BeleskeRepositoryImpl (
    private val localDataSource: BeleskeDao
): BeleskeRepository{

    override fun getAll(): Observable<List<Beleska>> {
        TODO("Not yet implemented")
    }

    override fun insert(beleskaEntity: BeleskaEntity): Completable {
        TODO("Not yet implemented")
    }


}