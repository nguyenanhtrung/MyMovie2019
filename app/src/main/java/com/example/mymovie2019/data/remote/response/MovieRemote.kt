package com.example.mymovie2019.data.remote.response

import com.example.mymovie2019.data.local.model.MovieTransferContract
import com.squareup.moshi.Json

data class MovieRemote(
        @Json(name = "adult")
        val adult: Boolean = false,
        @Json(name = "backdrop_path")
        val backdropPath: String? = "",
        @Json(name = "genre_ids")
        val genreIds: List<Int> = listOf(),
        @Json(name = "id")
        override var id: Int = 0,
        @Json(name = "original_language")
        val originalLanguage: String = "",
        @Json(name = "original_title")
        val originalTitle: String = "",
        @Json(name = "overview")
        val overview: String = "",
        @Json(name = "popularity")
        val popularity: Double = 0.0,
        @Json(name = "poster_path")
        override var imageUrl: String? = "",
        @Json(name = "release_date")
        override var releaseDate: String = "",
        @Json(name = "title")
        override var name: String = "",
        @Json(name = "video")
        val video: Boolean = false,
        @Json(name = "vote_average")
        override var rating: Double = 0.0,
        @Json(name = "vote_count")
        val voteCount: Int = 0
) : MovieTransferContract