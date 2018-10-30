package pokhilko.aleksandr.ru.data.converter

import pokhilko.aleksandr.ru.data.api.response.ItemResponse
import pokhilko.aleksandr.ru.domain.model.Item

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
object ItemConverter {

    fun fromNetwork(source: ItemResponse): Item {
        val images = source.images?: emptyList()
        return Item(images.map { ImageConverter.fromNetwork(it) })
    }

}