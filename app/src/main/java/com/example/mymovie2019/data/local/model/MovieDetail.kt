package com.example.mymovie2019.data.local.model

import com.example.mymovie2019.data.remote.response.Genre

data class MovieDetail(
    val backdropPath: String? = "",
    val genres: List<Genre>? = listOf(),
    val movieAbout: MovieAbout
)