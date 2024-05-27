package com.kirara.core.data.model.request

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.Part

data class UpdateUserRequest(
    val name: String? = null,
    val email: String? = null,
    val profilePicturePath: String? = null,
)
