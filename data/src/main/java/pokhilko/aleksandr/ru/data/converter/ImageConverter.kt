package pokhilko.aleksandr.ru.data.converter

import pokhilko.aleksandr.ru.data.api.response.ImageResponse
import pokhilko.aleksandr.ru.data.db.enitity.ImageEntity
import pokhilko.aleksandr.ru.domain.model.Image

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
object ImageConverter {

    fun fromNetwork(source: ImageResponse): Image {
        val title = source.description?: ""
        return Image(
                description = title,
                link = source.link,
                id = source.id)
    }

    fun toEntity(source: ImageResponse): ImageEntity {
        return ImageEntity(
                description = source.description?: "",
                id = source.id,
                link = source.link)
    }

    fun toEntity(source: Image): ImageEntity {
        return ImageEntity(
                description = source.description,
                id = source.id,
                link = source.link
        )
    }

    fun fromEntity(source: ImageEntity): Image {
        return Image(
                id = source.id,
                description = source.description,
                link = source.link
        )
    }
}