package pokhilko.aleksandr.ru.imgurapisample.presentation.view

import android.arch.paging.PagedList
import com.arellomobile.mvp.MvpView
import pokhilko.aleksandr.ru.domain.model.Image

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
interface MainView : MvpView {

    fun showImages(pagedList: PagedList<Image>)

}