package pokhilko.aleksandr.ru.imgurapisample.app

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import pokhilko.aleksandr.ru.data.api.ImgurApiService
import pokhilko.aleksandr.ru.data.api.RetrofitServiceCreator
import pokhilko.aleksandr.ru.data.db.AppDatabase
import pokhilko.aleksandr.ru.data.db.Database
import pokhilko.aleksandr.ru.data.repository.ImageDataRepository
import pokhilko.aleksandr.ru.data.repository.PageDataRepository
import pokhilko.aleksandr.ru.domain.repository.ImageRepository
import pokhilko.aleksandr.ru.domain.repository.PageRepository
import pokhilko.aleksandr.ru.domain.usecase.ImageUseCase
import pokhilko.aleksandr.ru.domain.usecase.PageUseCase
import timber.log.Timber


/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
class TheApp : Application() {

    val appModule = module {
        single<ImgurApiService> { RetrofitServiceCreator.createService() }
        single<AppDatabase> {
            Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    Database.NAME)
                    .build()
        }
        single<ImageRepository> { ImageDataRepository(get(), get()) }
        single<ImageUseCase> { ImageUseCase(get()) }
        single<SharedPreferences> { applicationContext.getSharedPreferences(PageDataRepository.PREFS_NAME, Context.MODE_PRIVATE) }
        single<PageRepository> { PageDataRepository(get()) }
        single<PageUseCase> { PageUseCase(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin(this, listOf(appModule))
    }
}