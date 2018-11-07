package pokhilko.aleksandr.ru.imgurapisample.app

import android.app.Application
import org.koin.android.ext.android.startKoin
import pokhilko.aleksandr.ru.imgurapisample.di.module.AppModule
import timber.log.Timber


/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
class TheApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin(this, listOf(AppModule(this).appModule))
    }
}