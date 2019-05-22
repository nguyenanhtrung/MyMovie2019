package com.example.mymovie2019.data.local.datasource.movie

import com.example.mymovie2019.data.local.model.*
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.remote.response.MovieDetailResponse

interface MovieLocalDataSource {

    fun getMoviesTypeVerticalItems(): MutableList<MoviesVerticalItem>

    fun parseToMovieDetail(movieDetailResponse: MovieDetailResponse, movieCreditResponse: MovieCreditResponse): MovieDetail

    fun getMovieTransfers(movieTransContracts: List<MovieTransferContract>): MutableList<MovieTransfer>

    fun saveMovies(movieEntities: List<MovieEntity>)

    fun getMovies(page: Int, movieType: MovieType): MutableList<MovieEntity>

    fun countMovieEntities(page: Int, movieType: MovieType): Long

    fun getMoviesSortByRating(offset: Int, movieType: MovieType): List<MovieEntity>
}