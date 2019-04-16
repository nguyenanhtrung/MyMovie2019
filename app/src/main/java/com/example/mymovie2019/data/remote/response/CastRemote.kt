package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

data class CastRemote(
    @Json(name = "adult")
    val adult: Boolean = false,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "known_for")
    val knownFor: List<KnownFor> = listOf(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "popularity")
    val popularity: Double = 0.0,
    @Json(name = "profile_path")
    val profilePath: String? = ""
)