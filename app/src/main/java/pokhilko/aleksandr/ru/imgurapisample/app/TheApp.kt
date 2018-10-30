package pokhilko.aleksandr.ru.imgurapisample.app

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import pokhilko.aleksandr.ru.data.api.ImgurApiService
import pokhilko.aleksandr.ru.data.api.RetrofitServiceCreator
import pokhilko.aleksandr.ru.data.repository.GalleryDataRepository
import pokhilko.aleksandr.ru.domain.repository.GalleryRepository
import pokhilko.aleksandr.ru.domain.usecase.GalleryUseCase
import timber.log.Timber

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
class TheApp : Application() {

    val appModule = module {
        single<ImgurApiService> { RetrofitServiceCreator.createService() }
        single<GalleryRepository> { GalleryDataRepository(get()) }
        single<GalleryUseCase> { GalleryUseCase(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin(this, listOf(appModule))
    }

}