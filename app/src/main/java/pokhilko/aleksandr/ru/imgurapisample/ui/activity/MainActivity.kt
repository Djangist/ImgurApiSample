package pokhilko.aleksandr.ru.imgurapisample.ui.activity

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import pokhilko.aleksandr.ru.domain.model.Gallery
import pokhilko.aleksandr.ru.imgurapisample.R
import pokhilko.aleksandr.ru.imgurapisample.presentation.presenter.MainPresenter
import pokhilko.aleksandr.ru.imgurapisample.presentation.view.MainView
import pokhilko.aleksandr.ru.imgurapisample.ui.adapter.ImageItem

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun showImages(gallery: Gallery) {
        val adapter = GroupAdapter<ViewHolder>()
        gallery.items.forEach {
            if( it.images.isNotEmpty() )
                adapter.add(Section(ImageItem(it.images.get(0))))
        }
        rvImages.adapter = adapter
    }

    @ProvidePresenter
    fun createPresenter(): MainPresenter {
        presenter = MainPresenter()
        return presenter
    }

    fun initViews(){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvImages.layoutManager = layoutManager
        rvImages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        btnSearch.setOnClickListener {
            presenter.searchImagesByTag(etTag.text.toString())
        }
    }
}
