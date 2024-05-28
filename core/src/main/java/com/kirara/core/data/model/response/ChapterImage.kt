package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class ChapterImage(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("alt")
    val alt: String,
)
