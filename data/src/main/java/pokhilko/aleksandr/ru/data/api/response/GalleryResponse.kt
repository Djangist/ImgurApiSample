package pokhilko.aleksandr.ru.data.api.response

import com.squareup.moshi.Json

/**
 * Created by Aleksandr Pokhilko on 29.10.2018
 */
data class GalleryResponse(
    val name: String,
    @Json(name = "display_name")
    val displayName: String
)