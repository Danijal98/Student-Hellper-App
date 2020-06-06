package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Raspored
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Resource
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.RasporedFilter

interface RasporedRepository{

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Raspored>>
    fun getAllByFilter(filter: RasporedFilter): Observable<List<Raspored>>

}