package pokhilko.aleksandr.ru.domain.repository

import io.reactivex.Single
import pokhilko.aleksandr.ru.domain.model.Gallery

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
interface GalleryRepository {

    fun getImagesByTag(tag: String): Single<Gallery>

}