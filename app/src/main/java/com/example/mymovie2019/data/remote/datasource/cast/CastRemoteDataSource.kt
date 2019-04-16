package com.example.mymovie2019.data.remote.datasource.cast

import com.example.mymovie2019.data.remote.response.CastMovieResponse
import com.example.mymovie2019.data.remote.response.CastTvShowResponse
import com.example.mymovie2019.data.remote.response.CastsResponse
import kotlinx.coroutines.Deferred

interface CastRemoteDataSource {

    fun getPopularCastsAsync(page: Int): Deferred<CastsResponse>

    fun getTvShowsOfCastAsync(castId: Int): Deferred<CastTvShowResponse>

    fun getMoviesOfCastAsync(castId: Int): Deferred<CastMovieResponse>
}