package com.example.mymovie2019.data.local.datasource.genre

import com.example.mymovie2019.data.local.database.entity.GenreEntity
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.Genre

interface GenreLocalDataSource {
    fun saveGenres(genres: List<GenreEntity>)

    fun getGenres(genreType: GenreCategory): MutableList<GenreEntity>

    fun countMovieGenres(typeName: String) : Long

    fun getGenreNames(genres:List<Genre>?): String

    fun updateMovieDetailId(movieDetailId: Long, genreIds: List<Long>?)
}