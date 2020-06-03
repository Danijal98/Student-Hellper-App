package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local.SharedPrefDataSource
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.datasources.local.UserDataSource
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.UserRepository
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.data.repositories.UserRepositoryImpl
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.presentation.viewmodel.UserViewModel

val userModule = module {

    viewModel {UserViewModel(userRepository = get())}

    single<UserRepository> {UserRepositoryImpl(userDataSource = get())}

    single<UserDataSource> {SharedPrefDataSource(sharedPreferences = get())}

}