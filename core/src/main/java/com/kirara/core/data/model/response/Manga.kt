package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class Manga(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("genre")
    val genre: List<String?>? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("rating")
    val rating: String? = null,
    @SerializedName("sinopsis")
    val sinopsis: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("rilis")
    val rilis: String? = null,
)