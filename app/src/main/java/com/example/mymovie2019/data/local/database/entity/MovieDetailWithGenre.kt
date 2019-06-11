package com.example.mymovie2019.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Relation

class MovieDetailWithGenre {
    @Embedded lateinit var movieDetail: MovieDetailEntity

    @Relation(parentColumn = "id",
                entityColumn = "movie_detail_id")
    lateinit var genres: List<GenreEntity>
}