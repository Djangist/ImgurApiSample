package pokhilko.aleksandr.ru.imgurapisample.ui.activity

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.paginate.Paginate
import kotlinx.android.synthetic.main.activity_main.*
import pokhilko.aleksandr.ru.domain.model.Image
import pokhilko.aleksandr.ru.imgurapisample.R
import pokhilko.aleksandr.ru.imgurapisample.presentation.presenter.MainPresenter
import pokhilko.aleksandr.ru.imgurapisample.presentation.view.MainView
import pokhilko.aleksandr.ru.imgurapisample.ui.adapter.ImagePagedListAdapter

class MainActivity : MvpAppCompatActivity(), MainView, Paginate.Callbacks {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val adapter = ImagePagedListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun showImages(pagedList: PagedList<Image>) {
        adapter.submitList(pagedList)
    }

    override fun onLoadMore() {
        presenter.loadMore()
    }

    override fun isLoading(): Boolean = presenter.isLoading()

    override fun hasLoadedAllItems(): Boolean = presenter.hasLoadAllImages()

    @ProvidePresenter
    fun createPresenter(): MainPresenter {
        presenter = MainPresenter()
        return presenter
    }

    fun initViews() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvImages.layoutManager = layoutManager
        rvImages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvImages.adapter = adapter

        Paginate.with(rvImages, this)

        rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if( (lastVisibleItem + 1 ) == totalItemCount ){
                    presenter.loadMore()
                }
            }
        })
    }
}