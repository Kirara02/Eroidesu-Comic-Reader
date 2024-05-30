package com.kirara.core.domain.usecase.manga.db

import com.kirara.core.data.datasource.local.entity.MangaEntity
import com.kirara.core.domain.repositories.manga.DbMangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import javax.inject.Inject

class InsertMangaDbUseCase @Inject constructor(
    private val dbMangaRepository: DbMangaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper,
) : BaseUseCase<MangaEntity, Long>(sharedPreferencesHelper){
    override suspend fun execute(params: MangaEntity, token: String?): Long {
        return dbMangaRepository.insertMangaDb(params)
    }
}