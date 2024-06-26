package com.kirara.core.domain.usecase.auth

import com.kirara.core.data.model.request.LoginRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<LoginRequest, Flow<BaseResponse<LoginData>>>(sharedPreferencesHelper){
    override suspend fun execute(params: LoginRequest, token: String?): Flow<BaseResponse<LoginData>> {
        return authRepository.login(params)
    }
}