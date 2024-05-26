package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T: Any>(
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("data")
    var data: T
)
