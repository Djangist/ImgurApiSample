package pokhilko.aleksandr.ru.data.repository

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import pokhilko.aleksandr.ru.data.api.ImgurApiService
import pokhilko.aleksandr.ru.data.converter.GalleryConverter
import pokhilko.aleksandr.ru.data.converter.ImageConverter
import pokhilko.aleksandr.ru.data.db.AppDatabase
import pokhilko.aleksandr.ru.domain.model.Image
import pokhilko.aleksandr.ru.domain.repository.ImageRepository
import timber.log.Timber

/**
 * Created by Aleksandr Pokhilko on 01.11.2018
 */
class ImageDataRepository(val db: AppDatabase,
                          val api: ImgurApiService) : ImageRepository {

    companion object {
        const val PAGE_SIZE = 10
    }

    override fun getImagesCountInStorage(): Single<Int> {
        return Single.fromCallable { db.imageDao().getImagesCount() }
    }

    override fun getImagesFromNetwork(page: Int): Single<Pair<List<Image>, Int>> {
        return api.imagesPaged(page)
                .map { GalleryConverter.fromNetwork(it.data) }
                .flatMap {
                    Single.fromCallable {
                        val list = mutableListOf<Image>()
                        it.items.forEach { item ->
                            list.addAll(item.images)
                        }
                        Pair(list.toList(), it.totalItems)
                    }
                }
                .doOnSuccess {
                    Timber.d("loaded from network, doOnSuccess -> page: $page")
                    db.imageDao().insert(it.first.map { img -> ImageConverter.toEntity(img) })
                }
    }

    override fun getImagesFromStorage(): Flowable<PagedList<Image>> {
        val dataSource = db.imageDao().getAllImages().map { ImageConverter.fromEntity(it) }
        val config = PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .build()
        return RxPagedListBuilder(dataSource, config)
                .buildFlowable(BackpressureStrategy.LATEST)
    }
}