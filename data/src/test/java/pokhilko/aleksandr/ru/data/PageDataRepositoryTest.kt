package pokhilko.aleksandr.ru.data

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import pokhilko.aleksandr.ru.data.repository.PageDataRepository
import pokhilko.aleksandr.ru.domain.repository.PageRepository
import org.mockito.Mockito.`when` as _when

/**
 * Created by Aleksandr Pokhilko on 06.11.2018
 */
@RunWith(MockitoJUnitRunner::class)
class PageDataRepositoryTest {

    lateinit var repository: PageRepository

    companion object {
        const val TESTED_PAGE = 5
    }

    @Before
    fun setup() {
        repository = mockk<PageDataRepository>()
    }

    @Test
    fun saveCurrentPage_isSuccess() {
        every { repository.saveCurrentPage(TESTED_PAGE) } just Runs
    }

    @Test
    fun getCurrentPage_isSuccess() {
        every { repository.getCurrentPage() } returns TESTED_PAGE
    }

}