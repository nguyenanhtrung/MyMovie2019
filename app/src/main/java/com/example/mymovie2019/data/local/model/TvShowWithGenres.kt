package com.example.mymovie2019.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mymovie2019.data.local.database.entity.GenreEntity
import com.example.mymovie2019.data.local.database.entity.TvShowEntity

class TvShowWithGenres {
    @Embedded
    lateinit var tvShowEntity: TvShowEntity

    @Relation(parentColumn = "id",
        entityColumn = "tv_show_id")
    lateinit var genres: List<GenreEntity>
}