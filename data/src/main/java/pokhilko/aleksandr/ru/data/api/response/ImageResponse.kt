package pokhilko.aleksandr.ru.data.api.response

import com.squareup.moshi.JsonClass

/**
 * Created by Aleksandr Pokhilko on 29.10.2018
 */
@JsonClass(generateAdapter = true)
data class ImageResponse(
        val description: String?,
        val link: String,
        val id: String
)