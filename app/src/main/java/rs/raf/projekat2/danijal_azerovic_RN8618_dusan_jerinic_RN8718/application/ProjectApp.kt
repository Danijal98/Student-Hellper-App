package rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.application

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.modules.beleskeModule
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.modules.coreModule
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.modules.rasporedModule
import rs.raf.projekat2.danijal_azerovic_RN8618_dusan_jerinic_RN8718.modules.userModule
import timber.log.Timber

class ProjectApp : Application(){

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
        initStetho()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            userModule,
            rasporedModule,
            beleskeModule
        )
        startKoin {
            androidLogger(Level.DEBUG)
            // Use application context
            androidContext(this@ProjectApp)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

}