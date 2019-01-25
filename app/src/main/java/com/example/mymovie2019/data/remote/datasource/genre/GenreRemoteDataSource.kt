package com.example.mymovie2019.data.remote.datasource.genre

import com.example.mymovie2019.data.remote.response.GenreResponse
import kotlinx.coroutines.Deferred

interface GenreRemoteDataSource {

    fun getGenresMovieFromServer(apiKey: String): Deferred<GenreResponse>

    fun getGenresTvShowFromServer(apiKey: String): Deferred<GenreResponse>
}