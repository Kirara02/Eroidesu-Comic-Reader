package com.kirara.core.data.model.request

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null
)
