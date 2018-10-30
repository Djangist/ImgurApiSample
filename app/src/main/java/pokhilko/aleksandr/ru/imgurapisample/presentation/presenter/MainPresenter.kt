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
class MainPresenter: MvpPresenter<MainView>(), KoinComponent {

    val useCase: GalleryUseCase by inject()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        useCase.getImagesByTag("venom")
                .schedulersIoToMain()
                .subscribe({ Timber.d("gal name ${it.name}")}, { Timber.e(it)})
    }

}