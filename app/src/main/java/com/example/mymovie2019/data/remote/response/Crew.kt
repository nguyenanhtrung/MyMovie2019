package com.example.mymovie2019.data.remote.response

import com.squareup.moshi.Json

data class Crew(
    @Json(name = "credit_id")
    val creditId: String = "",
    @Json(name = "department")
    val department: String = "",
    @Json(name = "gender")
    val gender: Int = 0,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "job")
    val job: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "profile_path")
    val profilePath: String? = ""
)