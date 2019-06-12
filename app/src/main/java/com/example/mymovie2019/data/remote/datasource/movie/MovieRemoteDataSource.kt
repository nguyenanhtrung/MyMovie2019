package com.example.mymovie2019.data.remote.datasource.movie

import com.example.mymovie2019.data.local.model.MovieType
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import com.example.mymovie2019.data.remote.response.MoviesResponse
import kotlinx.coroutines.Deferred

interface MovieRemoteDataSource {

     suspend fun getMoviesAsync(page: Int, movieType: MovieType): MoviesResponse
}