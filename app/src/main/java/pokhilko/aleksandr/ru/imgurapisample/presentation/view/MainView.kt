package pokhilko.aleksandr.ru.imgurapisample.presentation.view

import com.arellomobile.mvp.MvpView
import pokhilko.aleksandr.ru.domain.model.Gallery

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
interface MainView: MvpView {

    fun showImages(gallery: Gallery)

}