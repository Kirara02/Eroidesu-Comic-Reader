package com.kirara.core.domain.repositories.manga

import com.kirara.core.data.datasource.local.entity.MangaEntity
import kotlinx.coroutines.flow.Flow

interface DbMangaRepository {
    fun getMangasDb(): Flow<MutableList<MangaEntity>>
    fun getMangaByIdDb(id: Long): Flow<MangaEntity>
    suspend fun insertMangaDb(manga: MangaEntity): Long
    suspend fun deleteMangaDb(manga: MangaEntity): Int
}