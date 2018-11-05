package pokhilko.aleksandr.ru.domain.repository

import android.arch.paging.PagedList
import io.reactivex.Flowable
import io.reactivex.Single
import pokhilko.aleksandr.ru.domain.model.Image

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
interface ImageRepository {
    
    fun getImagesFromStorage(): Flowable<PagedList<Image>>
    fun getImagesFromNetwork(page: Int): Single<Pair<List<Image>, Int>>
    fun getImagesCountInStorage(): Single<Int>

}