package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mymovie2019.data.local.database.entity.CharacterEntity

@Dao
interface CharacterDao : BaseDao<CharacterEntity>{

    @Query("SELECT EXISTS(SELECT 1 FROM Character WHERE movie_id = (:movieId)  LIMIT 1)")
    fun checkExistCharsOfMovie(movieId: Int): Int
}