package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("refresh_token")
    var refreshToken: String,
    @SerializedName("token_type")
    var tokenType: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String
)