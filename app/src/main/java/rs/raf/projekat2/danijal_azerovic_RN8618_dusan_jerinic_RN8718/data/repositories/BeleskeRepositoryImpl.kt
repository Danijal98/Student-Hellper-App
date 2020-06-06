package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local.BeleskeDao
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.Beleska
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.models.BeleskaEntity
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.utilities.BeleskeFilter
import timber.log.Timber

class BeleskeRepositoryImpl (
    private val localDataSource: BeleskeDao
): BeleskeRepository{

    override fun getAll(): Observable<List<Beleska>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Beleska(
                        id = it.id.toString(),
                        title = it.title,
                        text = it.text,
                        archived = it.archived,
                        created = it.kreirano
                    )
                }
            }
    }

    override fun getAllByFilter(filter: BeleskeFilter): Observable<List<Beleska>> {
        val archived = filter.unarchived // true ako je switch upaljen
        if(archived){
            return localDataSource
                .getFiltriranoAndUnArchived(filter.filter, 0)
                .map {
                    it.map {
                        Beleska(
                            id = it.id.toString(),
                            title = it.title,
                            text = it.text,
                            archived = it.archived,
                            created = it.kreirano
                        )
                    }
                }
        }else{
            return localDataSource
                .getFiltrirano(filter.filter)
                .map {
                    Timber.e("$it")
                    it.map {
                        Beleska(
                            id = it.id.toString(),
                            title = it.title,
                            text = it.text,
                            archived = it.archived,
                            created = it.kreirano
                        )
                    }
                }
        }
    }

    override fun insert(beleskaEntity: BeleskaEntity): Completable {
        return localDataSource.insert(beleskaEntity)
    }

    override fun update(beleskaEntity: BeleskaEntity): Completable {
        return localDataSource.update(beleskaEntity)
    }

    override fun delete(beleskaEntity: BeleskaEntity): Completable {
        return localDataSource.delete(beleskaEntity)
    }

    override fun updateById(id: Int, title: String, text: String, archived: Int): Completable {
        return localDataSource.updateById(id,title,text,archived)
    }

}