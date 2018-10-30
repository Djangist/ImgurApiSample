package pokhilko.aleksandr.ru.data.repository

import io.reactivex.Single
import pokhilko.aleksandr.ru.data.api.ImgurApiService
import pokhilko.aleksandr.ru.data.converter.GalleryConverter
import pokhilko.aleksandr.ru.domain.model.Gallery
import pokhilko.aleksandr.ru.domain.repository.GalleryRepository

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
class GalleryDataRepository(val api: ImgurApiService): GalleryRepository {
    override fun getImagesByTag(tag: String): Single<Gallery> {
        return api.imagesByTag(tag)
                .map { GalleryConverter.fromNetwork(it?.data) }
    }
}