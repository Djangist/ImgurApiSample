package pokhilko.aleksandr.ru.data.converter

import pokhilko.aleksandr.ru.data.api.response.ImageResponse
import pokhilko.aleksandr.ru.domain.model.Image

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
object ImageConverter {

    fun fromNetwork(source: ImageResponse): Image {
        val title = source.description?: ""
        return Image(title, source.link)
    }

}