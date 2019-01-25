package com.example.mymovie2019.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mymovie2019.data.local.database.entity.GenreLocal

@Dao
interface GenreDao : BaseDao<GenreLocal> {

    @Query("SELECT * FROM GENRELOCAL LIMIT :limit OFFSET :offset")
    fun getGenres(type: String, offset: Int, limit: Int): LiveData<List<GenreLocal>>
}