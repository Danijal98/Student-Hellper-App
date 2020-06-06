package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.BeleskeRepository
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.contract.BeleskeContract
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.BeleskeState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.view.states.StatistikaState
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.BeleskeFilter
import timber.log.Timber
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

class BeleskeViewModel (
    private val beleskeRepository: BeleskeRepository
): ViewModel(), BeleskeContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val beleskeState: MutableLiveData<BeleskeState> = MutableLiveData()
    override val statistikaState: MutableLiveData<StatistikaState> = MutableLiveData()

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
                    //statistikaState.value = StatistikaState.Success(createStatisticsList(it))
                },
                {
                    beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    //statistikaState.value = StatistikaState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    private fun createStatisticsList(lista: List<Beleska>): List<Int>{
        val statisticsList: MutableList<Int> = mutableListOf(0,0,0,0,0)
        val datum = Date()
        val cal: Calendar = Calendar.getInstance()
        cal.time = datum
        cal.add(Calendar.DATE, -5)
        val dateBefore5Days: Date = cal.time
        var lastDay = datum
        var counter = 0
        var index = statisticsList.size
        for (beleska in lista.asReversed()){
            if (beleska.created.before(dateBefore5Days)) break
            if (!isSameDay(beleska.created,lastDay)) {
                statisticsList[index--] = counter
                counter = 0
                lastDay = beleska.created
            }
            counter++
        }
        statisticsList[index-1] = counter
        return statisticsList
    }


    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val localDate1: LocalDate = date1.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val localDate2: LocalDate = date2.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return localDate1.isEqual(localDate2)
    }

    override fun getBeleske() {
        val subscription = beleskeRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //beleskeState.value = BeleskeState.Success(it)
                    statistikaState.value = StatistikaState.Success(createStatisticsList(it))
                },
                {
                    //beleskeState.value = BeleskeState.Error("Error happened while fetching data from db")
                    statistikaState.value = StatistikaState.Error("Error happened while fetching data from db")
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

    override fun updateBeleskaById(id: Int, title: String, text: String, archived: Int) {
        val subscription = beleskeRepository
            .updateById(id,title,text,archived)
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

    override fun getStatistics() {

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