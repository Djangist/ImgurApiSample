package pokhilko.aleksandr.ru.domain.usecase

import io.reactivex.Single
import pokhilko.aleksandr.ru.domain.model.Gallery
import pokhilko.aleksandr.ru.domain.repository.GalleryRepository

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
class GalleryUseCase(val repository: GalleryRepository){

    fun getImagesByTag(tag: String): Single<Gallery> {
        return repository.getImagesByTag(tag)
    }

}