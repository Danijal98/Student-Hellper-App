package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories

import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local.RasporedDao
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.remote.RasporedService
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Raspored
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.RasporedEntity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Resource
import timber.log.Timber

class RasporedRepositoryImpl (
    private val localDataSource: RasporedDao,
    private val remoteDataSource: RasporedService
): RasporedRepository{

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Fetching data...")
                val entities = it.map {
                    RasporedEntity(
                        id = 0,
                        predmet = it.predmet,
                        tip = it.tip,
                        nastavnik = it.nastavnik,
                        grupe = it.grupe,
                        dan = it.dan,
                        termin = it.termin,
                        ucionica = it.ucionica
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Raspored>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    val grupeSplit = it.grupe.split(",")
                    Raspored(
                        id = it.id.toString(),
                        predmet = it.predmet,
                        tip = it.tip,
                        nastavnik = it.nastavnik,
                        grupe = grupeSplit.toMutableList(),
                        dan = it.dan,
                        termin = it.termin,
                        ucionica = it.ucionica
                    )
                }
            }
    }

}