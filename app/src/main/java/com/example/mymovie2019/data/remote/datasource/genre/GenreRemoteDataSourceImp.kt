package com.example.mymovie2019.data.remote.datasource.genre

import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.data.remote.service.ApiService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class GenreRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : GenreRemoteDataSource {


    override fun getGenresMovieFromServer(apiKey: String): Deferred<GenreResponse> {
        return apiService.getGenresOfMovieAsync(apiKey)
    }

    override fun getGenresTvShowFromServer(apiKey: String): Deferred<GenreResponse> {
        return apiService.getGenresOfTvShowAsync(apiKey)
    }
}