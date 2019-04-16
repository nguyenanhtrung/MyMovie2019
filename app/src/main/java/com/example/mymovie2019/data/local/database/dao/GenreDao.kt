package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mymovie2019.data.local.database.entity.GenreLocal

@Dao
interface GenreDao : BaseDao<GenreLocal> {

    @Query("SELECT * FROM Genre WHERE type LIKE :type")
    fun getGenres(type: String): MutableList<GenreLocal>

    @Query("SELECT COUNT(*) FROM Genre WHERE type = :typeName")
    fun countMovieGenres(typeName: String) : Long

}