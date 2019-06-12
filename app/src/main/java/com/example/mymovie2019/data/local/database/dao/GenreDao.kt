package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.mymovie2019.data.local.database.entity.GenreEntity

@Dao
interface GenreDao : BaseDao<GenreEntity> {

    @Query("SELECT * FROM Genre WHERE type LIKE :type")
    fun getGenres(type: String): MutableList<GenreEntity>

    @Query("SELECT COUNT(*) FROM Genre WHERE type = :typeName")
    fun countMovieGenres(typeName: String) : Long

    @Query("UPDATE  Genre SET movie_detail_id = (:movieDetailId) WHERE id IN (:genreIds)")
    fun updateMovieDetailId(movieDetailId: Long, genreIds: List<Long>)
}