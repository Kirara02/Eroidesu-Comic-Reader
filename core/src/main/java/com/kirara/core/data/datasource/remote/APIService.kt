package com.kirara.core.data.datasource.remote

import com.google.gson.annotations.SerializedName
import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.request.LoginRequest
import com.kirara.core.data.model.request.RefreshTokenRequest
import com.kirara.core.data.model.response.Manga
import com.kirara.core.data.model.response.MangaResponse
import com.kirara.core.data.model.request.RegisterRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.data.model.response.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface APIService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest) : AuthResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest) : BaseResponse<LoginData>

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") token: String) : AuthResponse

    @POST("auth/refresh-token")
    suspend fun refreshToken(@Body request: RefreshTokenRequest) : BaseResponse<LoginData>

    @GET("user")
    suspend fun getUser(@Header("Authorization") token: String) : BaseResponse<User>

    @GET("manga")
    suspend fun getMangas(
        @Header("Authorization") token: String,
    ): MangaResponse

    @GET("manga/popular")
    suspend fun getPopularMangas(
        @Header("Authorization") token: String,
    ): MangaResponse

    @GET("manga/{id}")
    suspend fun getMangaById(request: GetMangaByIdRequest): BaseResponse<Manga>

}