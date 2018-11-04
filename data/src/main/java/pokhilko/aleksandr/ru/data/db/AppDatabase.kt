package pokhilko.aleksandr.ru.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import pokhilko.aleksandr.ru.data.db.dao.ImageDao
import pokhilko.aleksandr.ru.data.db.enitity.ImageEntity

/**
 * Created by Aleksandr Pokhilko on 31.10.2018
 */
@Database(entities = [(ImageEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}
