package pokhilko.aleksandr.ru.imgurapisample.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import pokhilko.aleksandr.ru.data.utils.schedulersIoToMain
import pokhilko.aleksandr.ru.domain.usecase.GalleryUseCase
import pokhilko.aleksandr.ru.imgurapisample.presentation.view.MainView
import timber.log.Timber

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), KoinComponent {

    private val useCase: GalleryUseCase by inject()

    private var page = 0
    private var isLoading = false
    private var hasLoadedAllImages = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getLocalImages()
    }

    private fun getLocalImages() {
        useCase.getAllLocalImages()
                .schedulersIoToMain()
                .subscribe(
                        {
                            viewState.showImages(it)
                            if (it.size == 0) {
                                loadMore()
                            } else {
                                page = 1
                            }
                        },
                        { Timber.e(it) }
                )
    }

    fun loadMore() {
        if (!isLoading) {
            isLoading = true
            useCase.loadImagesFromNetwork(page++)
                    .schedulersIoToMain()
                    .subscribe(
                            { pair ->
                                isLoading = false
                                useCase.getImagesCountInStorage()
                                        .schedulersIoToMain()
                                        .subscribe({
                                            Timber.d("it = $it pair.second = ${pair.second}")
                                            hasLoadedAllImages = it == pair.second
                                            Timber.d("hasLoadedAllImages = $hasLoadedAllImages")
                                        },
                                                { Timber.e(it) })
                                Timber.d("subscribe")
                            },
                            { Timber.e(it) }
                    )
        }
    }

    fun isLoading(): Boolean = isLoading

    fun hasLoadAllImages(): Boolean = hasLoadedAllImages

}