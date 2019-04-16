package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

data class CastsResponse(
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    val castRemotes: List<CastRemote> = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int = 0,
    @Json(name = "total_results")
    val totalResults: Int = 0
)