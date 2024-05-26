package com.kirara.core.domain.usecase

import com.kirara.core.util.SharedPreferencesHelper

abstract class BaseUseCase <in Params, out T>(
    private val sharedPreferencesHelper: SharedPreferencesHelper
) {
    abstract suspend fun execute(params: Params, token: String? = null): T

    suspend fun executeWithToken(params: Params): T {
        val token = "Bearer ${sharedPreferencesHelper?.getAccessToken()}"
            ?: throw Exception("Token is not available")
        return execute(params, token)
    }
}