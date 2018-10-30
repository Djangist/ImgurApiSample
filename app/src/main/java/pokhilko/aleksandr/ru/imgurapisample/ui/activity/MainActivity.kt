package pokhilko.aleksandr.ru.imgurapisample.ui.activity

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import pokhilko.aleksandr.ru.imgurapisample.R
import pokhilko.aleksandr.ru.imgurapisample.presentation.presenter.MainPresenter
import pokhilko.aleksandr.ru.imgurapisample.presentation.view.MainView

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @ProvidePresenter
    fun createPresenter(): MainPresenter {
        presenter = MainPresenter()
        return presenter
    }
}
