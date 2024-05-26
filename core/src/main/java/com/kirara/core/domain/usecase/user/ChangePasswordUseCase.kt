package com.kirara.core.domain.usecase.user

import com.kirara.core.data.model.request.ChangePasswordRequest
import com.kirara.core.data.model.response.DefaultResponse
import com.kirara.core.domain.repositories.user.UserRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<ChangePasswordRequest, Flow<DefaultResponse>>(sharedPreferencesHelper){
    override suspend fun execute(
        params: ChangePasswordRequest,
        token: String?
    ): Flow<DefaultResponse> {
        return userRepository.changePassword(token ?: "", params)
    }
}