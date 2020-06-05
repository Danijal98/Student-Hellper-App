package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity

interface BeleskeRepository {

    fun getAll(): Observable<List<Beleska>>
    fun getAllByFilter(filter: String): Observable<List<Beleska>>
    fun insert(beleskaEntity: BeleskaEntity): Completable
    fun update(beleskaEntity: BeleskaEntity): Completable
    fun delete(beleskaEntity: BeleskaEntity): Completable

}