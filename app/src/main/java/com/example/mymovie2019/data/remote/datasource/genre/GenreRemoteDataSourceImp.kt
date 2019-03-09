package com.example.mymovie2019.data.remote.datasource.genre

import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.data.remote.service.ApiService
import javax.inject.Inject

class GenreRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : GenreRemoteDataSource {


    override suspend fun getGenresMovieFromServer(apiKey: String): GenreResponse {
        return apiService.getGenresOfMovieAsync(apiKey).await()
    }

    override suspend fun getGenresTvShowFromServer(apiKey: String): GenreResponse {
        return apiService.getGenresOfTvShowAsync(apiKey).await()
    }
}