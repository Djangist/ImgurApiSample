package pokhilko.aleksandr.ru.data.converter

import pokhilko.aleksandr.ru.data.api.response.GalleryResponse
import pokhilko.aleksandr.ru.domain.model.Gallery

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
object GalleryConverter {

    fun fromNetwork(source: GalleryResponse?): Gallery {
        if( source == null )
            throw IllegalArgumentException("gallery response is null")
        return Gallery(source.name, source.displayName, source.totalItems, source.items.map { ItemConverter.fromNetwork(it) })
    }

}