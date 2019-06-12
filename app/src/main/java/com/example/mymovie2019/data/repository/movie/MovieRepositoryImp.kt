package com.example.mymovie2019.data.repository.movie

import com.example.mymovie2019.data.local.database.entity.MovieEntity
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

    override suspend fun getMoviesAsync(page: Int, movieType: MovieType): MoviesResponse {
       return movieRemoteDataSource.getMoviesAsync(page, movieType)
    }

    override fun getMoviesTypeVerticalItems(): MutableList<MoviesVerticalItem> {
        return movieLocalDataSource.getMoviesTypeVerticalItems()
    }

    override fun getCreditMovieAsync(movieId: Int): Deferred<MovieCreditResponse> {
        return movieRemoteDataSource.getCreditMovieAsync(movieId)
    }

    override fun getMovieTransfers(movieTransContracts: List<MovieTransferContract>): MutableList<MovieTransfer> {
        return movieLocalDataSource.getMovieTransfers(movieTransContracts)
    }

    override fun saveMovies(movieEntities: List<MovieEntity>) {
       return movieLocalDataSource.saveMovies(movieEntities)
    }

    override fun getMovies(page: Int,movieType: MovieType): MutableList<MovieEntity> {
        return movieLocalDataSource.getMovies(page, movieType)
    }

    override fun countMovieEntities(page: Int, movieType: MovieType): Long {
        return movieLocalDataSource.countMovieEntities(page, movieType)
    }

    override fun getMoviesSortByRating(offset: Int, movieType: MovieType): List<MovieEntity> {
        return movieLocalDataSource.getMoviesSortByRating(offset, movieType)
    }
}