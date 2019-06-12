package com.example.mymovie2019.data.local.datasource.moviedetail

import com.example.mymovie2019.data.local.database.entity.MovieDetailWithGenre
import com.example.mymovie2019.data.local.model.MovieDetail
import com.example.mymovie2019.data.remote.response.MovieDetailResponse

interface MovieDetailLocalDataSource {

    fun checkExistsMovieDetail(movieId: Int): Boolean

    fun saveMovieDetail(movieDetailResponse: MovieDetailResponse)

    fun getMovieDetail(movieId: Int): MovieDetail
}