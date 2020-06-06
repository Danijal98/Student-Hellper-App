package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.BeleskeFilter

interface BeleskeRepository {

    // Beleske
    fun getAll(): Observable<List<Beleska>>
    fun getAllByFilter(filter: BeleskeFilter): Observable<List<Beleska>>
    fun insert(beleskaEntity: BeleskaEntity): Completable
    fun update(beleskaEntity: BeleskaEntity): Completable
    fun delete(beleskaEntity: BeleskaEntity): Completable
    fun updateById(id: Int, title: String, text: String, archived: Int): Completable

}