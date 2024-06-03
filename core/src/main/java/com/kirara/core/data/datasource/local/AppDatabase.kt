package com.kirara.core.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kirara.core.data.datasource.local.dao.MangaDao
import com.kirara.core.data.datasource.local.entity.MangaEntity
import com.kirara.core.util.Converters

@Database(
    entities = [MangaEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mangaDao() : MangaDao
}