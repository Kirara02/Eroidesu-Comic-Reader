package com.kirara.core.data.repositories.auth

import com.kirara.core.data.datasource.remote.APIService
import com.kirara.core.data.model.request.LoginRequest
import com.kirara.core.data.model.request.RefreshTokenRequest
import com.kirara.core.data.model.request.RegisterRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.domain.repositories.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : AuthRepository {
    override suspend fun register(request: RegisterRequest): Flow<AuthResponse> {
        return flowOf(apiService.register(request))
    }

    override suspend fun login(request: LoginRequest): Flow<BaseResponse<LoginData>> {
        return flowOf(apiService.login(request))
    }

    override suspend fun refreshToken(request: RefreshTokenRequest): Flow<BaseResponse<LoginData>> {
        return flowOf(apiService.refreshToken(request))
    }

    override suspend fun logout(token: String): Flow<AuthResponse> {
        return flowOf(apiService.logout(token))
    }
}