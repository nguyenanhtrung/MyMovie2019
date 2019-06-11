package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mymovie2019.data.local.database.entity.MovieEntity

@Dao
interface MovieDao : BaseDao<MovieEntity>{

    @Query("SELECT COUNT(*) FROM Movie WHERE type LIKE (:movieType) AND page = (:page)")
    fun countMovies(page: Int, movieType: String): Long

    @Query("SELECT * FROM Movie WHERE type LIKE (:movieType) AND page = (:page)")
    fun getMovies(page: Int, movieType: String): MutableList<MovieEntity>

    @Query("SELECT * FROM Movie WHERE type LIKE (:movieType) ORDER BY rating DESC LIMIT (:offset)")
    fun getMoviesSortByRating(offset: Int, movieType: String): List<MovieEntity>
}