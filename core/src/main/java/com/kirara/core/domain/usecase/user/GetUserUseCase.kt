package com.kirara.core.domain.usecase.user

import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.User
import com.kirara.core.domain.repositories.user.UserRepository
import com.kirara.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<String, Flow<BaseResponse<User>>>(){
    override suspend fun execute(params: String): Flow<BaseResponse<User>> {
        return userRepository.getUser(params)
    }
}