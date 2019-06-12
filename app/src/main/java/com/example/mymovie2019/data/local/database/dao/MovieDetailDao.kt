package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mymovie2019.data.local.database.entity.MovieDetailEntity
import com.example.mymovie2019.data.local.database.entity.MovieDetailWithGenre

@Dao
interface MovieDetailDao : BaseDao<MovieDetailEntity> {

    @Query("SELECT COUNT(*) FROM MovieDetail WHERE id = (:movieId) ")
    fun countMovieDetail(movieId: Int): Long

    @Query("SELECT * FROM MovieDetail WHERE id = (:movieId)")
    fun getMovieDetail(movieId: Int): MovieDetailWithGenre
}