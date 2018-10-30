package pokhilko.aleksandr.ru.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Aleksandr Pokhilko on 29.10.2018
 */
@JsonClass(generateAdapter = true)
data class GalleryResponse(
    val name: String,
    @Json(name = "display_name")
    val displayName: String,
    @Json(name = "total_items")
    val totalItems: Int,
    val items: List<ItemResponse>
)