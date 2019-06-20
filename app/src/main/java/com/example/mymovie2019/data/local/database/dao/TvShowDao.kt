package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mymovie2019.data.local.database.entity.TvShowEntity

@Dao
interface TvShowDao : BaseDao<TvShowEntity>{

    @Query("SELECT EXISTS(SELECT 1 FROM TvShow WHERE page = (:page) AND type LIKE :tvShowType  LIMIT 1)")
    fun isExistsTvShow(page: Int, tvShowType: String) : Int

    @Query("SELECT * FROM TvShow WHERE page = :page AND type LIKE :tvShowType")
    fun getTvShows(page: Int, tvShowType: String) : List<TvShowEntity>


}