package com.example.mymovie2019.data.local.datasource.genre

import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.Genre

interface GenreLocalDataSource {
    fun saveGenres(genres: List<GenreLocal>)

    fun getGenres(genreType: GenreCategory): MutableList<GenreLocal>

    fun countMovieGenres(typeName: String) : Long

    fun getGenreNames(genres:List<Genre>?): String
}