package com.kirara.core.domain.usecase.manga

import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Manga
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMangaByNameUseCase @Inject constructor(
    private val mangaRepository: MangaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<String, Flow<BaseResponse<Manga>>>(sharedPreferencesHelper){
    override suspend fun execute(params: String, token: String?): Flow<BaseResponse<Manga>> {
        return mangaRepository.getMangaByNameApiCall(token ?: "", params)
    }

}