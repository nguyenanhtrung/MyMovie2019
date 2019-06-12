package com.example.mymovie2019.data.remote.datasource.moviedetail

import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import com.example.mymovie2019.data.remote.service.ApiService
import com.example.mymovie2019.utils.AppKey
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MovieDetailRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : MovieDetailRemoteDataSource {

    override fun getMovieDetailAsync(movieId: Int): Deferred<MovieDetailResponse> {
        return apiService.getMovieDetailAsync(movieId, AppKey.API_KEY)
    }
}