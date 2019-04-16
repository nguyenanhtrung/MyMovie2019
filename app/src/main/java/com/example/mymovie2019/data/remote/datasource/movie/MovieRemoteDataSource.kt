package com.example.mymovie2019.data.remote.datasource.movie

import com.example.mymovie2019.data.local.model.MovieType
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import com.example.mymovie2019.data.remote.response.MoviesResponse
import kotlinx.coroutines.Deferred

interface MovieRemoteDataSource {
     fun getMoviesAsync(page: Int, movieType: MovieType): Deferred<MoviesResponse>

     fun getMovieDetailAsync(movieId: Int): Deferred<MovieDetailResponse>

     fun getCreditMovieAsync(movieId: Int): Deferred<MovieCreditResponse>
}