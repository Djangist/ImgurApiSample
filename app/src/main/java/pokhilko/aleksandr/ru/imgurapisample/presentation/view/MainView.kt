package pokhilko.aleksandr.ru.imgurapisample.presentation.view

import android.arch.paging.PagedList
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import pokhilko.aleksandr.ru.domain.model.Image

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {

    fun showImages(pagedList: PagedList<Image>)

}