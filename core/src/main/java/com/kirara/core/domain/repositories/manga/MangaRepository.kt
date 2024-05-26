package com.kirara.core.domain.repositories.manga

import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Manga
import com.kirara.core.data.model.response.MangaResponse
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun getMangasApiCall(token: String) : Flow<MangaResponse>
    suspend fun getPopularMangasApiCall(token: String) : Flow<MangaResponse>
    suspend fun getMangaByIdApiCall(token: String, request: GetMangaByIdRequest) : Flow<BaseResponse<Manga>>
}