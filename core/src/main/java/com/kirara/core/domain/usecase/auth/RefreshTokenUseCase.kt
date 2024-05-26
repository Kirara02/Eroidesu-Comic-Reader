package com.kirara.core.domain.usecase.auth

import com.kirara.core.data.model.request.RefreshTokenRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<RefreshTokenRequest, Flow<BaseResponse<LoginData>>>(sharedPreferencesHelper){
    override suspend fun execute(params: RefreshTokenRequest, token: String?): Flow<BaseResponse<LoginData>> {
        return authRepository.refreshToken(params)
    }
}