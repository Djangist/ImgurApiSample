package pokhilko.aleksandr.ru.imgurapisample.app

import android.app.Application
import android.arch.persistence.room.Room
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import pokhilko.aleksandr.ru.data.api.ImgurApiService
import pokhilko.aleksandr.ru.data.api.RetrofitServiceCreator
import pokhilko.aleksandr.ru.data.db.AppDatabase
import pokhilko.aleksandr.ru.data.db.Database
import pokhilko.aleksandr.ru.data.repository.ImageDataRepository
import pokhilko.aleksandr.ru.domain.repository.ImageRepository
import pokhilko.aleksandr.ru.domain.usecase.GalleryUseCase
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
        single<GalleryUseCase> { GalleryUseCase(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin(this, listOf(appModule))
    }
}