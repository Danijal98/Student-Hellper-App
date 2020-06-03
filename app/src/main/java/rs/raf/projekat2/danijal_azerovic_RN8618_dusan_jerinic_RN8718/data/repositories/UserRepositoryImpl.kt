package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local.UserDataSource

class UserRepositoryImpl(private val userDataSource: UserDataSource): UserRepository{

    override fun isLogged(): Observable<Boolean> {
        return userDataSource.getUserData()
    }

    override fun insertUser(name: String) {
        userDataSource.insertUser(name)
    }

}