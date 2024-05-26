package com.kirara.core.domain.usecase.auth

import com.kirara.core.data.model.request.RegisterRequest
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<RegisterRequest, Flow<AuthResponse>>(){
    override suspend fun execute(params: RegisterRequest): Flow<AuthResponse> {
        return authRepository.register(params)
    }
}