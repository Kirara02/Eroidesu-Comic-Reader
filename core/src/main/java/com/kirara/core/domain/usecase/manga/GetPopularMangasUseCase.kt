package com.kirara.core.domain.usecase.manga

import com.kirara.core.data.model.response.MangaResponse
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMangasUseCase @Inject constructor(
    private val mangaRepository: MangaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<Unit, Flow<MangaResponse>>(sharedPreferencesHelper) {
    override suspend fun execute(params: Unit, token: String?): Flow<MangaResponse> {
        return mangaRepository.getPopularMangasApiCall(token ?: "")
    }
}