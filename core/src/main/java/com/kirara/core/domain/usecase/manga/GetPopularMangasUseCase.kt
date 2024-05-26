package com.kirara.core.domain.usecase.manga

import com.kirara.core.data.model.response.MangaResponse
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMangasUseCase @Inject constructor(
    private val mangaRepository: MangaRepository
) : BaseUseCase<String, Flow<MangaResponse>>() {
    override suspend fun execute(token: String): Flow<MangaResponse> {
        return mangaRepository.getPopularMangasApiCall(token)
    }
}