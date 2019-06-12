package com.example.mymovie2019.data.remote.datasource.moviedetail

import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import kotlinx.coroutines.Deferred

interface MovieDetailRemoteDataSource {

    fun getMovieDetailAsync(movieId: Int): Deferred<MovieDetailResponse>
}