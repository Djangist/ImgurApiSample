package pokhilko.aleksandr.ru.domain.repository

/**
 * Created by Aleksandr Pokhilko on 05.11.2018
 */
interface PageRepository {

    fun getCurrentPage(): Int
    fun saveCurrentPage(page: Int)

}