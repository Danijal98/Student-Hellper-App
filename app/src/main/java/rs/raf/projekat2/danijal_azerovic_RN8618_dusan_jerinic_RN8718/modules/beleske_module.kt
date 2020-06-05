package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local.MainDataBase
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.BeleskeRepository
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.BeleskeRepositoryImpl
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.BeleskeViewModel

val beleskeModule = module {

    viewModel { BeleskeViewModel(beleskeRepository = get()) }

    single<BeleskeRepository> {BeleskeRepositoryImpl(localDataSource = get())}

    single {get<MainDataBase>().getBeleskeDao()}

}