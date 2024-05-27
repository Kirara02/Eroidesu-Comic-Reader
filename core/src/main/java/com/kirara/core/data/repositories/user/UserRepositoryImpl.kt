package com.kirara.core.data.repositories.user

import com.kirara.core.data.datasource.remote.APIService
import com.kirara.core.data.model.request.ChangePasswordRequest
import com.kirara.core.data.model.request.UpdateUserRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.DefaultResponse
import com.kirara.core.data.model.response.User
import com.kirara.core.domain.repositories.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : UserRepository {
    override suspend fun getUser(token: String): Flow<BaseResponse<User>> {
        return flowOf(apiService.getUser(token))
    }

    override suspend fun updateUser(
        token: String,
        name: String,
        email: String,
        profilePicturePath: String?
    ): Flow<BaseResponse<User>> {
        val nameRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val emailRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), email)

        val profilePicturePart = profilePicturePath?.let {
            val file = File(it)
            val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("profile_picture", file.name, requestBody)
        }

        val response = apiService.updateUser(
            token = token,
            name = nameRequestBody,
            email = emailRequestBody,
            profilePicture = profilePicturePart
        )
        return flowOf(response)
    }

    override suspend fun changePassword(
        token: String,
        request: ChangePasswordRequest
    ): Flow<DefaultResponse> {
        return flowOf(apiService.changePassword(token, request))
    }
}