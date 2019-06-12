package com.example.mymovie2019.data.local.datasource.moviedetail

import com.example.mymovie2019.data.local.database.dao.MovieDetailDao
import com.example.mymovie2019.data.local.database.entity.MovieDetailEntity
import com.example.mymovie2019.data.local.database.entity.MovieDetailWithGenre
import com.example.mymovie2019.data.local.model.MovieAbout
import com.example.mymovie2019.data.local.model.MovieDetail
import com.example.mymovie2019.data.remote.response.Genre
import com.example.mymovie2019.data.remote.response.MovieDetailResponse
import javax.inject.Inject

class MovieDetailLocalDataSourceImp @Inject constructor(private val movieDetailDao: MovieDetailDao) : MovieDetailLocalDataSource {

    override fun checkExistsMovieDetail(movieId: Int): Boolean {
        val count = movieDetailDao.countMovieDetail(movieId)
        return count != 0L
    }

    override fun saveMovieDetail(movieDetailResponse: MovieDetailResponse) {
        with(movieDetailResponse) {
            val movieDetailEntity = MovieDetailEntity(
                id ?: 0,
                backdropPath ?: "",
                budget?.toLong() ?: 0L,
                overview ?: "",
                originalTitle ?: "",
                status ?: "",
                runtime ?: 0,
                releaseDate ?: "",
                revenue?.toLong() ?: 0L,
                homepage ?: ""
            )
            movieDetailDao.insertData(movieDetailEntity)
        }
    }

    override fun getMovieDetail(movieId: Int): MovieDetail {
        val movieDetailWithGenre = movieDetailDao.getMovieDetail(movieId)
        val genres = movieDetailWithGenre.genres.map {
            Genre(it.id.toInt(), it.name)
        }
        val movieDetailEntity = movieDetailWithGenre.movieDetail
        val movieAbout = MovieAbout(
            movieDetailEntity.overview,
            movieDetailEntity.originalTitle,
            movieDetailEntity.status,
            movieDetailEntity.runtime,
            genres,
            movieDetailEntity.premiere,
            movieDetailEntity.budget,
            movieDetailEntity.revenue,
            movieDetailEntity.homePage
        )
        return MovieDetail(
            movieDetailEntity.backdropPath,
            movieAbout
        )
    }
}