package com.example.mymovie2019.data.repository.movie

import com.example.mymovie2019.data.local.datasource.movie.MovieLocalDataSource
import com.example.mymovie2019.data.remote.datasource.movie.MovieRemoteDataSource

interface MovieRepository : MovieRemoteDataSource, MovieLocalDataSource {
}