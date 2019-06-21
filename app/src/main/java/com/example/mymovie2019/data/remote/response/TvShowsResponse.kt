package com.example.mymovie2019.data.remote.response


import com.squareup.moshi.Json

data class TvShowsResponse(
    @Json(name = "page")
    val page: Int? = 0, // 1
    @Json(name = "results")
    val tvShowRemotes: List<TvShowRemote?>? = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int? = 0, // 1000
    @Json(name = "total_results")
    val totalResults: Int? = 0 // 20000
): BaseResponse()