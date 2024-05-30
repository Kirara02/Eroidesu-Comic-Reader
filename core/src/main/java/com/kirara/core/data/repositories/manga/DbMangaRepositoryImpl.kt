package com.kirara.core.data.repositories.manga

import com.kirara.core.data.datasource.local.AppDatabase
import com.kirara.core.data.datasource.local.entity.MangaEntity
import com.kirara.core.domain.repositories.manga.DbMangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DbMangaRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : DbMangaRepository {
    override fun getMangasDb(): Flow<MutableList<MangaEntity>> {
        return flowOf(db.mangaDao().getMangas())
    }

    override fun getMangaByIdDb(id: Long): Flow<MangaEntity> {
        return flowOf(db.mangaDao().getMangaById(id))
    }

    override suspend fun insertMangaDb(manga: MangaEntity): Long {
        return db.mangaDao().insertManga(manga)
    }

    override suspend fun deleteMangaDb(manga: MangaEntity): Int {
        return db.mangaDao().deleteManga(manga)
    }
}