package pokhilko.aleksandr.ru.data.api.response

import com.squareup.moshi.JsonClass

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
@JsonClass(generateAdapter = true)
data class ItemResponse(
        val images: List<ImageResponse>?
)