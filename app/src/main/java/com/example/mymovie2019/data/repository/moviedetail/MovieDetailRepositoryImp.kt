package com.example.mymovie2019.data.repository.moviedetail

import com.example.mymovie2019.data.local.datasource.moviedetail.MovieDetailLocalDataSource
import com.example.mymovie2019.data.local.model.MovieDetail
import com.example.mymovie2019.data.remote.datasource.moviedetail.MovieDetailRemoteDataSource
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(
    private val movieDetailLocalDataSource: MovieDetailLocalDataSource,
    private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource
) : MovieDetailRepository {


    override fun getMovieDetailAsync(movieId: Int): Deferred<MovieDetailResponse> {
        return movieDetailRemoteDataSource.getMovieDetailAsync(movieId)
    }

    override fun checkExistsMovieDetail(movieId: Int): Boolean {
        return movieDetailLocalDataSource.checkExistsMovieDetail(movieId)
    }

    override fun getMovieDetail(movieId: Int): MovieDetail {
        return movieDetailLocalDataSource.getMovieDetail(movieId)
    }

    override fun saveMovieDetail(movieDetailResponse: MovieDetailResponse) {
        movieDetailLocalDataSource.saveMovieDetail(movieDetailResponse)
    }
}