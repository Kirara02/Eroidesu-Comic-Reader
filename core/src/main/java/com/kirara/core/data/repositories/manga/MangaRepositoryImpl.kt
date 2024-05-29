package com.kirara.core.data.repositories.manga

import com.kirara.core.data.datasource.remote.APIService
import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Chapter
import com.kirara.core.data.model.response.ChapterImage
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

    override suspend fun getMangaByIdApiCall( token: String, id: Int): Flow<BaseResponse<Manga>> {
        return flowOf(apiService.getMangaById(token, id))
    }

    override suspend fun getMangaByNameApiCall(
        token: String,
        name: String
    ): Flow<BaseResponse<Manga>> {
        return flowOf(apiService.getMangaByName(token, name))
    }

    override suspend fun getMangaListChapters(token: String, id: Int): Flow<BaseResponse<List<Chapter>>> {
        return flowOf(apiService.getMangaListChapters(token, id))
    }

    override suspend fun getMangaChapterById(
        token: String,
        id: Int
    ): Flow<BaseResponse<List<ChapterImage>>> {
        return flowOf(apiService.getMangaChapterById(token, id))
    }

}