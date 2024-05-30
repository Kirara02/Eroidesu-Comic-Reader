package com.kirara.core.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kirara.core.data.datasource.local.entity.MangaEntity

@Dao
interface MangaDao {

    @Query("Select * FROM table_manga")
    fun getMangas() : MutableList<MangaEntity>

    @Query("select * FROM table_manga where id=:id")
    fun getMangaById(id: Long): MangaEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManga(mangaEntity: MangaEntity): Long

    @Delete
    suspend fun deleteManga(mangaEntity: MangaEntity): Int
}