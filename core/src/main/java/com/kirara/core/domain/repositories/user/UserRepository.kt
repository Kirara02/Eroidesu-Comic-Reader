package com.kirara.core.domain.repositories.user

import com.kirara.core.data.model.request.ChangePasswordRequest
import com.kirara.core.data.model.request.UpdateUserRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.DefaultResponse
import com.kirara.core.data.model.response.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(token: String) : Flow<BaseResponse<User>>
    suspend fun updateUser(token: String, name: String, email: String, profilePicture: String?): Flow<BaseResponse<User>>
    suspend fun changePassword(token: String, request: ChangePasswordRequest) : Flow<DefaultResponse>
}