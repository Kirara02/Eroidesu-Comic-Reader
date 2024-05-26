package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class MangaResponse(
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("data")
    var data: MutableList<Manga>? = null,
    @SerializedName("pagination")
    var pagination: Pagination? = null
)
