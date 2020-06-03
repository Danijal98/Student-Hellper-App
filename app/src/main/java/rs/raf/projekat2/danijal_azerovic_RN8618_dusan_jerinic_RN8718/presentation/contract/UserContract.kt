package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract

import androidx.lifecycle.LiveData

interface UserContract {

    interface ViewModel{

        val logged: LiveData<Boolean>

        fun isLogged()

        fun insertUser(name: String)

    }

}