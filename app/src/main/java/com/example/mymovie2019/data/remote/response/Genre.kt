package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

data class Genre(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = ""
)