package com.example.mymovie2019.data.local.datasource.movie

import com.example.mymovie2019.data.local.database.dao.MovieDao
import com.example.mymovie2019.data.local.database.entity.MovieEntity
import com.example.mymovie2019.data.local.model.*
import com.example.mymovie2019.data.local.model.MovieType.*
import com.example.mymovie2019.data.remote.response.Cast
import com.example.mymovie2019.data.remote.response.MovieCreditResponse
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import javax.inject.Inject

class MovieLocalDataSourceImp @Inject constructor(private val movieDao: MovieDao) : MovieLocalDataSource {


    override fun getMoviesTypeVerticalItems(): MutableList<MoviesVerticalItem> {
        val movieTypes = mutableListOf<MoviesVerticalItem>()
        movieTypes += MoviesVerticalItem(
            POPULAR.ordinal,
            "Popular Movies",
            mutableListOf(MovieItem(itemType = ItemType.ListLoading))
        )
        movieTypes += MoviesVerticalItem(
            UPCOMING.ordinal,
            "Upcoming Movies",
            mutableListOf(MovieItem(itemType = ItemType.ListLoading))
        )
        movieTypes += MoviesVerticalItem(
            TOP_RATED.ordinal,
            "TopRated Movies",
            mutableListOf(MovieItem(itemType = ItemType.ListLoading))
        )
        return movieTypes
    }

    override fun parseToMovieDetail(movieDetailResponse: MovieDetailResponse, movieCreditResponse: MovieCreditResponse): MovieDetail {

        return MovieDetail(
            movieDetailResponse.backdropPath,
            movieDetailResponse.genres,
            createMovieAbout(movieDetailResponse, movieCreditResponse.cast)
        )
    }

    private fun createMovieAbout(movieDetailResponse: MovieDetailResponse, casts: List<Cast>): MovieAbout {
        return MovieAbout(
            movieDetailResponse.overview,
            movieDetailResponse.originalTitle,
            movieDetailResponse.status,
            movieDetailResponse.runtime,
            movieDetailResponse.genres,
            movieDetailResponse.releaseDate,
            movieDetailResponse.budget,
            movieDetailResponse.revenue!!.toLong(),
            movieDetailResponse.homepage,
            casts
        )
    }

    override fun saveMovies(movieEntities: List<MovieEntity>) {
        return movieDao.insertDatas(movieEntities)
    }

    override fun getMovies(page: Int, movieType: MovieType): MutableList<MovieEntity> {
        return movieDao.getMovies(page,movieType.name)
    }

    override fun countMovieEntities(page: Int, movieType: MovieType): Long {
        return movieDao.countMovies(page,movieType.name)
    }

    private fun parseToMovieTransfer(movieTransferContract: MovieTransferContract): MovieTransfer {
        return MovieTransfer(movieTransferContract = movieTransferContract)
    }

    override fun getMovieTransfers(movieTransContracts: List<MovieTransferContract>): MutableList<MovieTransfer>{
        return movieTransContracts.map {
            parseToMovieTransfer(it)
        }.toMutableList()
    }

    override fun getMoviesSortByRating(offset: Int, movieType: MovieType): List<MovieEntity> {
        return movieDao.getMoviesSortByRating(offset, movieType.name)
    }
}