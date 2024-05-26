package com.kirara.core.domain.usecase.auth

import com.kirara.core.data.model.request.RefreshTokenRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<RefreshTokenRequest, Flow<BaseResponse<LoginData>>>(){
    override suspend fun execute(params: RefreshTokenRequest): Flow<BaseResponse<LoginData>> {
        return authRepository.refreshToken(params)
    }
}