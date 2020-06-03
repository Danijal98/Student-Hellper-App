package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.UserRepository
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.UserContract
import timber.log.Timber

class UserViewModel (private val userRepository: UserRepository): ViewModel(), UserContract.ViewModel{

    override val logged: MutableLiveData<Boolean> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun isLogged() {
        val subscription = userRepository
            .isLogged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    logged.value = it
                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("On complete")
                }
            )
    }

    override fun insertUser(name: String) {
        userRepository.insertUser(name)
        //TODO posto funkcija ne vraca completable da li mora kroz rxJava
    }

    override fun onCleared() {
        subscriptions.dispose()
        super.onCleared()
    }
}