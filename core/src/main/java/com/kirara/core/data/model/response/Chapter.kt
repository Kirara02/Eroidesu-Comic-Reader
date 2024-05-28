package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class Chapter(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("chapter")
    val chapter: String? = null,
    @SerializedName("rilis")
    val rilis: String? = null
)
