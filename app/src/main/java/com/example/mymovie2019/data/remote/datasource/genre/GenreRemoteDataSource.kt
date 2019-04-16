package com.example.mymovie2019.data.remote.datasource.genre

import com.example.mymovie2019.data.remote.response.GenreResponse

interface GenreRemoteDataSource {

    suspend fun getGenresMovieFromServer(): GenreResponse

    suspend fun getGenresTvShowFromServer(apiKey: String): GenreResponse
}