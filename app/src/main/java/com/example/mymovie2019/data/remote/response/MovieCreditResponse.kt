package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

data class MovieCreditResponse(
    @Json(name = "cast")
    val cast: List<Cast> = listOf(),
    @Json(name = "crew")
    val crew: List<Crew> = listOf(),
    @Json(name = "id")
    val id: Int = 0
)