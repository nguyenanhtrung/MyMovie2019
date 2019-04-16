package com.example.mymovie2019.data.remote.datasource.cast

import com.example.mymovie2019.data.remote.response.CastMovieResponse
import com.example.mymovie2019.data.remote.response.CastTvShowResponse
import com.example.mymovie2019.data.remote.response.CastsResponse
import com.example.mymovie2019.data.remote.service.ApiService
import com.example.mymovie2019.utils.AppKey
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CastRemoteDataSourceImp @Inject constructor(private val apiService: ApiService): CastRemoteDataSource {

    override fun getPopularCastsAsync(page: Int): Deferred<CastsResponse> {
        return apiService.getPopularCasts(page,AppKey.API_KEY)
    }

    override fun getTvShowsOfCastAsync(castId: Int): Deferred<CastTvShowResponse> {
        return apiService.getTvShowsOfCastAsync(castId, AppKey.API_KEY)
    }

    override fun getMoviesOfCastAsync(castId: Int): Deferred<CastMovieResponse> {
        return apiService.getMoviesOfCastAsync(castId, AppKey.API_KEY)
    }
}