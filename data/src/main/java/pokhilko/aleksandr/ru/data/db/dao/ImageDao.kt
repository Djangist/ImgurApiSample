package pokhilko.aleksandr.ru.data.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import pokhilko.aleksandr.ru.data.db.Tables
import pokhilko.aleksandr.ru.data.db.enitity.ImageEntity

/**
 * Created by Aleksandr Pokhilko on 31.10.2018
 */
@Dao
interface ImageDao {

    @Query("SELECT * FROM ${Tables.IMAGES}")
    fun getAllImages(): DataSource.Factory<Int, ImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: ImageEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<ImageEntity>): List<Long>

    @Query("SELECT COUNT(*) FROM ${Tables.IMAGES}")
    fun getImagesCount(): Int

}