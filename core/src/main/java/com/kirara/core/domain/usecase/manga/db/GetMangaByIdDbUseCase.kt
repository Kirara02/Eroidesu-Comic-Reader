package com.kirara.core.domain.usecase.manga.db

import com.kirara.core.data.datasource.local.entity.MangaEntity
import com.kirara.core.domain.repositories.manga.DbMangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMangaByIdDbUseCase @Inject constructor(
    private val dbMangaRepository: DbMangaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<Long, Flow<MangaEntity>>(sharedPreferencesHelper){
    override suspend fun execute(params: Long, token: String?): Flow<MangaEntity> {
        return dbMangaRepository.getMangaByIdDb(params)
    }
}