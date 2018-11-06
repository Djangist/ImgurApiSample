package pokhilko.aleksandr.ru.data

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import pokhilko.aleksandr.ru.data.repository.ImageDataRepository
import pokhilko.aleksandr.ru.domain.repository.ImageRepository

/**
 * Created by Aleksandr Pokhilko on 06.11.2018
 */
@RunWith(MockitoJUnitRunner::class)
class ImageDataRepositoryTest {

    lateinit var repository: ImageRepository

    companion object {
        const val TESTED_IMAGES_COUNT = 20
    }

    @Before
    fun setup() {
        repository = mockk<ImageDataRepository>()
    }

    @Test
    fun getImagesCount_success() {
        every { repository.getImagesCountInStorage() } returns Single.just(TESTED_IMAGES_COUNT)
    }

    @Test
    fun getImagesCount_failure() {
        every { repository.getImagesCountInStorage() } throws RuntimeException("Room db error")
    }

}