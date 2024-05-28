package com.kirara.core.data.datasource.remote

import com.google.gson.annotations.SerializedName
import com.kirara.core.data.model.request.ChangePasswordRequest
import com.kirara.core.data.model.request.GetMangaByIdRequest
import com.kirara.core.data.model.request.LoginRequest
import com.kirara.core.data.model.request.RefreshTokenRequest
import com.kirara.core.data.model.response.Manga
import com.kirara.core.data.model.response.MangaResponse
import com.kirara.core.data.model.request.RegisterRequest
import com.kirara.core.data.model.request.UpdateUserRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.data.model.response.Chapter
import com.kirara.core.data.model.response.DefaultResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.data.model.response.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

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

    @Multipart
    @POST("user/update")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part profilePicture: MultipartBody.Part?
    ): BaseResponse<User>

    @POST("user/change-password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body request: ChangePasswordRequest
    ) : DefaultResponse

    @GET("manga")
    suspend fun getMangas(
        @Header("Authorization") token: String,
    ): MangaResponse

    @GET("manga/popular")
    suspend fun getPopularMangas(
        @Header("Authorization") token: String,
    ): MangaResponse

    @GET("manga/{id}")
    suspend fun getMangaById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): BaseResponse<Manga>

    @GET("manga/title/{name}")
    suspend fun getMangaByName(
        @Header("Authorization") token: String,
        @Path("name") name: String
    ): BaseResponse<Manga>

    @GET("manga/{id}/chapter")
    suspend fun getMangaListChapters(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : BaseResponse<List<Chapter>>


}