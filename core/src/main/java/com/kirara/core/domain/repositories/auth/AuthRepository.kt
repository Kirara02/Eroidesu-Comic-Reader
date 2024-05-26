package com.kirara.core.domain.repositories.auth

import com.kirara.core.data.model.request.LoginRequest
import com.kirara.core.data.model.request.RefreshTokenRequest
import com.kirara.core.data.model.request.RegisterRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.data.model.response.LoginData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(request: RegisterRequest) : Flow<AuthResponse>
    suspend fun login(request: LoginRequest) : Flow<BaseResponse<LoginData>>
    suspend fun refreshToken(request: RefreshTokenRequest) : Flow<BaseResponse<LoginData>>
    suspend fun logout(token: String) : Flow<AuthResponse>
}