package pokhilko.aleksandr.ru.data

import junit.framework.Assert.assertTrue
import org.junit.Test
import pokhilko.aleksandr.ru.data.api.response.ImageResponse
import pokhilko.aleksandr.ru.data.converter.ImageConverter
import pokhilko.aleksandr.ru.domain.model.Image

/**
 * Created by Aleksandr Pokhilko on 08.11.2018
 */
class ImageConverterTest {

    private val domainImage = Image(
            description = "Test",
            link = "http://test.com",
            id = "1"
    )

    private val responseImage = ImageResponse(
            description = "Test",
            link = "http://test.com",
            id = "1"
    )

    private val emptyResponse = ImageResponse("", "", "")

    @Test
    fun get_domain_model_success() {
        val model = ImageConverter.fromNetwork(responseImage)
        assertTrue(model.description.equals(domainImage.description))
        assertTrue(model.link == domainImage.link)
        assertTrue(model.id == domainImage.id)
    }

    @Test
    fun get_domain_model_failure() {
        val model = ImageConverter.fromNetwork(emptyResponse)
        assertTrue(model.description == domainImage.description)
    }
}