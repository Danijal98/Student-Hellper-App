package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local

import android.content.SharedPreferences
import androidx.core.content.edit
import io.reactivex.Observable

class SharedPrefDataSource (private val sharedPreferences: SharedPreferences) : UserDataSource{

    companion object{
        const val USER_NAME_KEY = "userName"
    }

    override fun getUserData(): Observable<Boolean> {
        return Observable.fromCallable {
            val name = sharedPreferences.getString(USER_NAME_KEY, "")
            name.isNotBlank()
        }
    }

    override fun insertUser(name: String) {
        sharedPreferences.edit{
            putString(USER_NAME_KEY, name)
            apply()
        }
    }

}