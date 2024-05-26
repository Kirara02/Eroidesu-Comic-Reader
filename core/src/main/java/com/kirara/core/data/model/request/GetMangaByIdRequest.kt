package com.kirara.core.data.model.request

import retrofit2.http.Header
import retrofit2.http.Path

data class GetMangaByIdRequest(
    @Path("id") val id: Int
)
