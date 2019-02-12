package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

class GenreResponse(
    @Json(name = "genres")
    val genres: List<Genre> = listOf()
) : BaseResponse()