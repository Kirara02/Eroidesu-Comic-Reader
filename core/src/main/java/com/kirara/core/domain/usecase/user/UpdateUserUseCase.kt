package com.kirara.core.domain.usecase.user

import com.kirara.core.data.model.request.UpdateUserRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.User
import com.kirara.core.domain.repositories.user.UserRepository
import com.kirara.core.domain.usecase.BaseUseCase
import com.kirara.core.util.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    sharedPreferencesHelper: SharedPreferencesHelper
) : BaseUseCase<UpdateUserRequest, Flow<BaseResponse<User>>>(sharedPreferencesHelper){
    override suspend fun execute(
        params: UpdateUserRequest,
        token: String?
    ): Flow<BaseResponse<User>> {
        return userRepository.updateUser(
            token ?: "",
            params.name ?: "",
            params.email ?: "",
            params.profilePicturePath
        )
    }
}