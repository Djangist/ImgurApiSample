package pokhilko.aleksandr.ru.data.repository

import android.content.SharedPreferences
import pokhilko.aleksandr.ru.domain.repository.PageRepository

/**
 * Created by Aleksandr Pokhilko on 05.11.2018
 */
class PageDataRepository(val prefs: SharedPreferences): PageRepository {

    companion object {
        const val PREFS_NAME = "page_prefs"
        const val ITEM_PAGE = "item_page"
    }

    override fun getCurrentPage(): Int = prefs.getInt(ITEM_PAGE, 0)

    override fun saveCurrentPage(page: Int) {
        prefs.edit().putInt(ITEM_PAGE, page).apply()
    }
}