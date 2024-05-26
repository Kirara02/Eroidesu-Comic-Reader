package com.kirara.core.domain.usecase.auth

import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<String, Flow<AuthResponse>>(){
    override suspend fun execute(params: String): Flow<AuthResponse> {
        return authRepository.logout(params)
    }
}