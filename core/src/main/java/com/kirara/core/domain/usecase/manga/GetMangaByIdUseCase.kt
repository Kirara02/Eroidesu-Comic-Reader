package com.kirara.core.domain.usecase.manga

import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Manga
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMangaByIdUseCase @Inject constructor(
    private val mangaRepository: MangaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<GetMangaByIdRequest, Flow<BaseResponse<Manga>>>(sharedPreferencesHelper){
    override suspend fun execute(params: GetMangaByIdRequest, token: String?): Flow<BaseResponse<Manga>> {
        return mangaRepository.getMangaByIdApiCall(token ?: "", params)
    }

}