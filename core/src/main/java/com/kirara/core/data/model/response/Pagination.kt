package com.kirara.core.data.model.response

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    @SerializedName("current_page")
    val currentPage: Int? = null,
    @SerializedName("from")
    val from: Int? = null,
    @SerializedName("to")
    val to: Int? = null
)
