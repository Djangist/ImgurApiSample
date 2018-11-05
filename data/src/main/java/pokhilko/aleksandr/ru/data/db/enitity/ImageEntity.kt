package pokhilko.aleksandr.ru.data.db.enitity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import pokhilko.aleksandr.ru.data.db.Tables

/**
 * Created by Aleksandr Pokhilko on 31.10.2018
 */
@Entity(tableName = Tables.IMAGES)
data class ImageEntity(
        @PrimaryKey(autoGenerate = true)
        val uid: Int = 0,
        val description: String,
        val link: String,
        val id: String
)