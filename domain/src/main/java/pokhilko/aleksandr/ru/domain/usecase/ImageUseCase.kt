package pokhilko.aleksandr.ru.domain.usecase

import android.arch.paging.PagedList
import io.reactivex.Flowable
import io.reactivex.Single
import pokhilko.aleksandr.ru.domain.model.Image
import pokhilko.aleksandr.ru.domain.repository.ImageRepository

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
class ImageUseCase(private val repository: ImageRepository){

    fun getAllLocalImages(): Flowable<PagedList<Image>> {
        return repository.getImagesFromStorage()
    }

    fun loadImagesFromNetwork(page: Int): Single<Pair<List<Image>, Int>> {
        return repository.getImagesFromNetwork(page)
    }

    fun getImagesCountInStorage(): Single<Int> {
        return repository.getImagesCountInStorage()
    }

}