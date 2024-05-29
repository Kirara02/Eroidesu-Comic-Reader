package com.kirara.core.domain.usecase.manga

import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.ChapterImage
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMangaChapterByIdUseCase @Inject constructor(
    private val mangaRepository: MangaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<Int, Flow<BaseResponse<List<ChapterImage>>>>(sharedPreferencesHelper){
    override suspend fun execute(
        params: Int,
        token: String?
    ): Flow<BaseResponse<List<ChapterImage>>> {
        return mangaRepository.getMangaChapterById(token ?: "", params)
    }
}