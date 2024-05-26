package com.kirara.core.domain.repositories.user

import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(token: String) : Flow<BaseResponse<User>>
}