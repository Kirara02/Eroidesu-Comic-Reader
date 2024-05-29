package com.kirara.core.domain.repositories.manga

import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Chapter
import com.kirara.core.data.model.response.ChapterImage
import com.kirara.core.data.model.response.Manga
import com.kirara.core.data.model.response.MangaResponse
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun getMangasApiCall(token: String) : Flow<MangaResponse>
    suspend fun getPopularMangasApiCall(token: String) : Flow<MangaResponse>
    suspend fun getMangaByIdApiCall(token: String, id: Int) : Flow<BaseResponse<Manga>>
    suspend fun getMangaByNameApiCall(token: String, name: String) : Flow<BaseResponse<Manga>>
    suspend fun getMangaListChapters(token: String, id: Int) : Flow<BaseResponse<List<Chapter>>>
    suspend fun getMangaChapterById(token: String, id: Int) : Flow<BaseResponse<List<ChapterImage>>>
}