package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.BeleskeRepository
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.BeleskeContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.BeleskeState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.BeleskeFilter
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BeleskeViewModel (
    private val beleskeRepository: BeleskeRepository
): ViewModel(), BeleskeContract.ViewModel{


    private val subscriptions = CompositeDisposable()
    override val beleskeState: MutableLiveData<BeleskeState> = MutableLiveData()

    private val publishSubject: PublishSubject<BeleskeFilter> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                beleskeRepository
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
                    beleskeState.value = BeleskeState.Success(it)
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getBeleske() {
        val subscription = beleskeRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskeState.Success(it)
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertBeleska(beleskaEntity: BeleskaEntity) {
        val subscription = beleskeRepository
            .insert(beleskaEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Complete")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateBeleska(beleskaEntity: BeleskaEntity) {
        val subscription = beleskeRepository
            .update(beleskaEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Complete")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteBeleska(beleskaEntity: BeleskaEntity) {
        val subscription = beleskeRepository
            .delete(beleskaEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Deleted $beleskaEntity")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getBeleskeByFilter(filter: BeleskeFilter) {
        publishSubject.onNext(filter)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}