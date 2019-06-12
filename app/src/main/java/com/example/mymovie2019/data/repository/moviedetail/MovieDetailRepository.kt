package com.example.mymovie2019.data.repository.moviedetail

import com.example.mymovie2019.data.local.datasource.moviedetail.MovieDetailLocalDataSource
import com.example.mymovie2019.data.remote.datasource.moviedetail.MovieDetailRemoteDataSource

interface MovieDetailRepository : MovieDetailLocalDataSource, MovieDetailRemoteDataSource {
}