package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local

import io.reactivex.Completable
import io.reactivex.Observable

interface UserDataSource {
    fun getUserData(): Observable<Boolean>
    fun insertUser(name: String): Completable
}