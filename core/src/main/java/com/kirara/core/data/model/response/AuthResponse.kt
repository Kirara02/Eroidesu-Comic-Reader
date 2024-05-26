package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("message")
    var message: String
)
