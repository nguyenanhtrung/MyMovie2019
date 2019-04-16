package com.example.mymovie2019.data.repository.movie

import com.example.mymovie2019.data.local.datasource.movie.MovieLocalDataSource
import com.example.mymovie2019.data.local.model.*
import com.example.mymovie2019.data.remote.datasource.movie.MovieRemoteDataSource
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import com.example.mymovie2019.data.remote.response.MoviesResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource,
                                             private val movieLocalDataSource: MovieLocalDataSource) : MovieRepository {

    override fun getMoviesAsync(page: Int, movieType: MovieType): Deferred<MoviesResponse> {
        return movieRemoteDataSource.getMoviesAsync(page,movieType)
    }

    override fun getMoviesTypeVerticalItems(): MutableList<MoviesVerticalItem> {
        return movieLocalDataSource.getMoviesTypeVerticalItems()
    }

    override fun getMovieDetailAsync(movieId: Int): Deferred<MovieDetailResponse> {
        return movieRemoteDataSource.getMovieDetailAsync(movieId)
    }

    override fun parseToMovieDetail(movieDetailResponse: MovieDetailResponse, movieCreditResponse: MovieCreditResponse): MovieDetail {
        return movieLocalDataSource.parseToMovieDetail(movieDetailResponse, movieCreditResponse)
    }

    override fun getCreditMovieAsync(movieId: Int): Deferred<MovieCreditResponse> {
        return movieRemoteDataSource.getCreditMovieAsync(movieId)
    }

    override fun getMovieTransfers(movieTransContracts: List<MovieTransferContract>): MutableList<MovieTransfer> {
        return movieLocalDataSource.getMovieTransfers(movieTransContracts)
    }
}