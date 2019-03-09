package com.example.mymovie2019.data.repository.movie

import com.example.mymovie2019.data.local.datasource.movie.MovieLocalDataSource
import com.example.mymovie2019.data.local.model.MovieType
import com.example.mymovie2019.data.local.model.MoviesVerticalItem
import com.example.mymovie2019.data.remote.datasource.movie.MovieRemoteDataSource
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
}