package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

data class CastTvShowResponse(
    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "cast")
    val castTvShows: List<TvShowRemote> = listOf()
)