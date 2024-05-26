package com.kirara.core.domain.usecase.manga

import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Manga
import com.kirara.core.domain.repositories.manga.MangaRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMangaByIdUseCase @Inject constructor(
    private val mangaRepository: MangaRepository
) : BaseUseCase<GetMangaByIdRequest, Flow<BaseResponse<Manga>>>(){
    override suspend fun execute(params: GetMangaByIdRequest): Flow<BaseResponse<Manga>> {
        return mangaRepository.getMangaByIdApiCall(params)
    }

}