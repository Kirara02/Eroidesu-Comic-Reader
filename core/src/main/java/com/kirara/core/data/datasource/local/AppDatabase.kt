package com.kirara.core.data.datasource.local

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.kirara.core.data.datasource.local.dao.MangaDao
import com.kirara.core.data.datasource.local.entity.MangaEntity

@Database(
    entities = [MangaEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun mangaDao() : MangaDao
}