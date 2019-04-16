package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

data class CastMovieResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "cast")
    val castMovies: List<MovieRemote>

)