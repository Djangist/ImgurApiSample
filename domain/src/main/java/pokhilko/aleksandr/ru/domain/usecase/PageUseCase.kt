package pokhilko.aleksandr.ru.domain.usecase

import pokhilko.aleksandr.ru.domain.repository.PageRepository

/**
 * Created by Aleksandr Pokhilko on 05.11.2018
 */
class PageUseCase(val repository: PageRepository) {

    fun saveCurrentPage(page: Int) {
        repository.saveCurrentPage(page)
    }

    fun getCurrentPage() = repository.getCurrentPage()

}