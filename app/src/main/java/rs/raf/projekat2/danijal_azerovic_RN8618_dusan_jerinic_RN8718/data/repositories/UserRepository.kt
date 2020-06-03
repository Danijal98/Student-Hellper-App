package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories

import io.reactivex.Observable

interface UserRepository {
    fun isLogged(): Observable<Boolean>
    fun insertUser(name: String)
}