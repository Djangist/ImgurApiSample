package pokhilko.aleksandr.ru.imgurapisample.di.module

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
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

/**
 * Created by Aleksandr Pokhilko on 07.11.2018
 */
class AppModule(val applicationContext: Context) {

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

}