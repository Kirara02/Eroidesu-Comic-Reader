package com.kirara.core.data.repositories.manga

import com.kirara.core.data.datasource.remote.APIService
import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Manga
import com.kirara.core.data.model.response.MangaResponse
import com.kirara.core.domain.repositories.manga.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : MangaRepository {
    override suspend fun getMangasApiCall(token: String): Flow<MangaResponse> {
        return flowOf(apiService.getMangas(token))
    }

    override suspend fun getPopularMangasApiCall(token: String): Flow<MangaResponse> {
        return flowOf(apiService.getPopularMangas(token))
    }

    override suspend fun getMangaByIdApiCall( token: String, request: GetMangaByIdRequest): Flow<BaseResponse<Manga>> {
        return flowOf(apiService.getMangaById(token, request))
    }


}