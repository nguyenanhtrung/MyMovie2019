package com.example.mymovie2019.data.remote.response

import com.example.mymovie2019.data.local.model.MovieType
import com.squareup.moshi.Json

class MoviesResponse(
        @Json(name = "page")
        var page: Int = 0,
        @Json(name = "results")
        var movieRemotes: List<MovieRemote> = listOf(),
        @Json(name = "total_pages")
        var totalPages: Int = 0,
        @Json(name = "total_results")
        var totalResults: Int = 0
) : BaseResponse() {
        var movieType: MovieType = MovieType.POPULAR
}