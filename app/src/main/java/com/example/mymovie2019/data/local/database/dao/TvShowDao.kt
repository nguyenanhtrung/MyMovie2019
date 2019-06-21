package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.mymovie2019.data.local.database.entity.TvShowEntity
import com.example.mymovie2019.data.local.model.TvShowWithGenres

@Dao
interface TvShowDao : BaseDao<TvShowEntity>{

    @Query("SELECT EXISTS(SELECT 1 FROM TvShow WHERE page = (:page) AND type LIKE :tvShowType  LIMIT 1)")
    fun isExistsTvShow(page: Int, tvShowType: String) : Int

    @Transaction
    @Query("SELECT * FROM TvShow WHERE page = :page AND type LIKE :tvShowType")
    fun getTvShows(page: Int, tvShowType: String) : List<TvShowWithGenres>


}