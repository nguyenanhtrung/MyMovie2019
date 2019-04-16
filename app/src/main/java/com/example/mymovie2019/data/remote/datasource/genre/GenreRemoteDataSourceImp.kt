package com.example.mymovie2019.data.remote.datasource.genre

import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.data.remote.service.ApiService
import com.example.mymovie2019.utils.AppKey
import javax.inject.Inject

class GenreRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : GenreRemoteDataSource {


    override suspend fun getGenresMovieFromServer(): GenreResponse {
        return apiService.getGenresOfMovieAsync(AppKey.API_KEY).await()
    }

    override suspend fun getGenresTvShowFromServer(apiKey: String): GenreResponse {
        return apiService.getGenresOfTvShowAsync(apiKey).await()
    }
}