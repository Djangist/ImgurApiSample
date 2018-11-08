package pokhilko.aleksandr.ru.imgurapisample

import android.content.Context
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.AutoCloseKoinTest
import pokhilko.aleksandr.ru.data.api.ImgurApiService
import pokhilko.aleksandr.ru.imgurapisample.di.module.AppModule

/**
 * Created by Aleksandr Pokhilko on 07.11.2018
 */
class ApiTest : AutoCloseKoinTest() {

    val api: ImgurApiService by inject()

    @Before
    fun setup() {
        val context = mockk<Context>()
        startKoin(listOf(AppModule(context).appModule))
    }

    @Test
    fun getImages_Response_first_page_success() {
        api.imagesPaged(0)
                .test()
                .assertComplete()
    }

    @Test
    fun getImages_Response_first_page_items_not_empty() {
        api.imagesPaged(0)
                .test()
                .assertValue { it.data?.items!!.isNotEmpty() }
    }

    @Test
    fun getImages_Response_items_is_empty() {
        api.imagesPaged(22)
                .test()
                .assertValue { it.data?.items!!.isEmpty() }
    }

}