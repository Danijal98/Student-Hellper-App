package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Resource
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.RasporedRepository
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.RasporedContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.RasporedState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.RasporedFilter
import timber.log.Timber

class RasporedViewModel (
    private val rasporedRepository: RasporedRepository
): ViewModel(), RasporedContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val rasporedState: MutableLiveData<RasporedState> = MutableLiveData()

    private val publishSubject: PublishSubject<RasporedFilter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .switchMap {
                rasporedRepository
                    .getAllByFilter(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    rasporedState.value = RasporedState.Success(it)
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchRaspored() {
        val subscription = rasporedRepository
            .fetchAll()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it){
                        is Resource.Loading -> rasporedState.value = RasporedState.Loading
                        is Resource.Success -> rasporedState.value = RasporedState.DataFetched
                        is Resource.Error -> rasporedState.value = RasporedState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getRaspored() {
        val subscription = rasporedRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    rasporedState.value = RasporedState.Success(it)
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getRasporedByFilter(filter: RasporedFilter) {
        publishSubject.onNext(filter)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}