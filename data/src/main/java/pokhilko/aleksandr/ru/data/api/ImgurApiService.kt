package pokhilko.aleksandr.ru.data.api

import io.reactivex.Single
import pokhilko.aleksandr.ru.data.api.response.GalleryResponse
import pokhilko.aleksandr.ru.data.api.response.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Aleksandr Pokhilko on 29.10.2018
 */
interface ImgurApiService {

    @GET("gallery/t/{tag}")
    fun imagesByTag(@Path("tag") tag: String): Single<ServerResponse<GalleryResponse>>

}