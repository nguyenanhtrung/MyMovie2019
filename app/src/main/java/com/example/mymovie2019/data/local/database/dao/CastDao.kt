package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mymovie2019.data.local.database.entity.CastEntity
import com.example.mymovie2019.data.local.model.CastLocal

@Dao
interface CastDao : BaseDao<CastEntity> {

    @Query("SELECT CastOfMovie.id, CastOfMovie.name, CastOfMovie.profile_path AS profilePath, Character.name AS characterName FROM CastOfMovie INNER JOIN Character ON CastOfMovie.id = Character.cast_id WHERE Character.movie_id = (:movieId)")
    fun getCastLocals(movieId: Int) : List<CastLocal>
}