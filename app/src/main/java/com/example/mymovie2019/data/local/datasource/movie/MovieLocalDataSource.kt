package com.example.mymovie2019.data.local.datasource.movie

import com.example.mymovie2019.data.local.model.MovieDetail
import com.example.mymovie2019.data.local.model.MovieTransfer
import com.example.mymovie2019.data.local.model.MovieTransferContract
import com.example.mymovie2019.data.local.model.MoviesVerticalItem
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.remote.response.MovieDetailResponse

interface MovieLocalDataSource {

    fun getMoviesTypeVerticalItems(): MutableList<MoviesVerticalItem>

    fun parseToMovieDetail(movieDetailResponse: MovieDetailResponse, movieCreditResponse: MovieCreditResponse): MovieDetail

    fun getMovieTransfers(movieTransContracts: List<MovieTransferContract>): MutableList<MovieTransfer>
}