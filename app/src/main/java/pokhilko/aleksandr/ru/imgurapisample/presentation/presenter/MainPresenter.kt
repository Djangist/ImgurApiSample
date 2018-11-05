package pokhilko.aleksandr.ru.imgurapisample.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import pokhilko.aleksandr.ru.data.utils.schedulersIoToMain
import pokhilko.aleksandr.ru.domain.usecase.ImageUseCase
import pokhilko.aleksandr.ru.domain.usecase.PageUseCase
import pokhilko.aleksandr.ru.imgurapisample.presentation.view.MainView
import timber.log.Timber

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), KoinComponent {

    private val imageUseCase: ImageUseCase by inject()
    private val pageUseCase: PageUseCase by inject()

    private var page = 0
    private var isLoading = false
    private var hasLoadedAllImages = false

    fun getLocalImages() {
        page = pageUseCase.getCurrentPage()
        Timber.d("current page = $page")
        imageUseCase.getAllLocalImages()
                .schedulersIoToMain()
                .subscribe(
                        {
                            viewState.showImages(it)
                            if (it.isEmpty()) {
                                loadMore()
                            }
                        },
                        { Timber.e(it) }
                )
    }

    fun loadMore() {
        if (!isLoading) {
            isLoading = true

            imageUseCase.loadImagesFromNetwork(page++)
                    .schedulersIoToMain()
                    .subscribe(
                            { pair ->
                                isLoading = false

                                pageUseCase.saveCurrentPage(page)

                                imageUseCase.getImagesCountInStorage()
                                        .schedulersIoToMain()
                                        .subscribe({
                                            Timber.d("it = $it pair.second = ${pair.second}")
                                            hasLoadedAllImages = it == pair.second
                                            Timber.d("hasLoadedAllImages = $hasLoadedAllImages")
                                        },
                                                { Timber.e(it) })
                            },
                            { Timber.e(it) }
                    )
        }
    }

    fun isLoading(): Boolean = isLoading

    fun hasLoadAllImages(): Boolean = hasLoadedAllImages

}