package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class DefaultResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
