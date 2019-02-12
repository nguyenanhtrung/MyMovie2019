package com.example.mymovie2019.data.remote.service

import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.utils.AppKey
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    fun getGenresOfMovieAsync(@Query(AppKey.API_KEY_PARAMETER) apiKey: String): Deferred<GenreResponse>

    @GET("genre/tv/list")
    fun getGenresOfTvShowAsync(@Query(AppKey.API_KEY_PARAMETER) apiKey: String): Deferred<GenreResponse>
}