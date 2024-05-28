package com.kirara.core.domain.usecase.manga

import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Chapter
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMangaListChaptersUseCase @Inject constructor(
    private val mangaRepository: MangaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<Int, Flow<BaseResponse<List<Chapter>>>>(sharedPreferencesHelper){
    override suspend fun execute(params: Int, token: String?): Flow<BaseResponse<List<Chapter>>> {
        return mangaRepository.getMangaListChapters(token ?: "", params)
    }
}