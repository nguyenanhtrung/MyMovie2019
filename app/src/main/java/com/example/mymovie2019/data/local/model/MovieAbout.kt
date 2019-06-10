package com.example.mymovie2019.data.local.model

import android.os.Parcelable
import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.data.remote.response.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieAbout(
    val overview: String? = "",
    val originalTitle: String? = "",
    val status: String? = "",
    val runtime: Int? = 0,
    val category: List<Genre>? = listOf(),
    val premiere: String? = "",
    val budget: Int? = 0,
    val revenue: Long? = 0,
    val homePage: String? = "",
    val casts: List<Cast>? = listOf()
): Parcelable