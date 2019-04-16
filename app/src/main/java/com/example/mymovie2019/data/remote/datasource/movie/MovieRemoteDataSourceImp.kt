package com.example.mymovie2019.data.remote.datasource.movie

import com.example.mymovie2019.data.local.model.MovieType
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import com.example.mymovie2019.data.remote.response.MoviesResponse
import com.example.mymovie2019.data.remote.service.ApiService
import com.example.mymovie2019.utils.AppKey
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MovieRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : MovieRemoteDataSource {

    override fun getMoviesAsync(page: Int, movieType: MovieType): Deferred<MoviesResponse> {
        return when(movieType) {
            MovieType.POPULAR -> apiService.getPopularMovies(page,AppKey.API_KEY)
            MovieType.UPCOMING -> apiService.getUpcomingMovies(page,AppKey.API_KEY)
            MovieType.TOP_RATED -> apiService.getTopRatedMovies(page, AppKey.API_KEY)
        }
    }

    override fun getMovieDetailAsync(movieId: Int): Deferred<MovieDetailResponse> {
        return apiService.getMovieDetailAsync(movieId,AppKey.API_KEY)
    }

    override fun getCreditMovieAsync(movieId: Int): Deferred<MovieCreditResponse> {
        return apiService.getCreditMovieAsync(movieId,AppKey.API_KEY)
    }
}