package com.kirara.core.data.repositories.user

import com.kirara.core.data.datasource.remote.APIService
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.User
import com.kirara.core.domain.repositories.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : UserRepository {
    override suspend fun getUser(token: String): Flow<BaseResponse<User>> {
        return flowOf(apiService.getUser(token))
    }
}