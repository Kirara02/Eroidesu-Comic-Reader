package com.kirara.core.domain.usecase.auth

import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.domain.repositories.auth.AuthRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<Unit, Flow<AuthResponse>>(sharedPreferencesHelper){
    override suspend fun execute(params: Unit, token: String?): Flow<AuthResponse> {
        return authRepository.logout(token ?: "")
    }
}