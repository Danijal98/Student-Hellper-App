package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local.MainDataBase
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.remote.RasporedService
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.RasporedRepository
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.RasporedRepositoryImpl
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.RasporedViewModel

val rasporedModule = module {

    viewModel {RasporedViewModel(rasporedRepository = get())}

    single<RasporedRepository> {RasporedRepositoryImpl(localDataSource = get(), remoteDataSource = get())}

    single {get<MainDataBase>().getRasporedDao()}

    single<RasporedService> {create(retrofit = get())}

}