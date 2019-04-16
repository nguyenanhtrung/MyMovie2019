package com.example.mymovie2019.data.remote.response

import com.example.mymovie2019.data.local.model.MovieTransferContract
import com.squareup.moshi.Json

data class TvShowRemote(
        @Json(name = "backdrop_path")
        val backdropPath: String? = "",
        @Json(name = "character")
        val character: String? = "",
        @Json(name = "credit_id")
        val creditId: String? = "",
        @Json(name = "episode_count")
        val episodeCount: Int? = 0,
        @Json(name = "first_air_date")
        override var releaseDate: String = "",
        @Json(name = "genre_ids")
        val genreIds: List<Int?>? = listOf(),
        @Json(name = "id")
        override var id: Int = 0,
        @Json(name = "name")
        override var name: String = "",
        @Json(name = "origin_country")
        val originCountry: List<String>? = listOf(),
        @Json(name = "original_language")
        val originalLanguage: String? = "",
        @Json(name = "original_name")
        val originalName: String? = "",
        @Json(name = "overview")
        val overview: String? = "",
        @Json(name = "popularity")
        val popularity: Double? = 0.0,
        @Json(name = "poster_path")
        override var imageUrl: String? = "",
        @Json(name = "vote_average")
        override var rating: Double = 0.0,
        @Json(name = "vote_count")
        val voteCount: Int? = 0
) : MovieTransferContract