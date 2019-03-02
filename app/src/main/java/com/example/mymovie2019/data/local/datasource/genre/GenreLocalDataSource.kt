package com.example.mymovie2019.data.local.datasource.genre

import androidx.lifecycle.LiveData
import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.GenreCategory

interface GenreLocalDataSource {
    fun saveGenres(genres: List<GenreLocal>): List<Long>

    fun getGenres(genreCategory: GenreCategory, offSet: Int, limit: Int): LiveData<List<GenreLocal>>

    fun countGenres() : Long
}