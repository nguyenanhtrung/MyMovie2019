package com.example.mymovie2019.data.remote.datasource.genre

import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.GenreResponse

interface GenreRemoteDataSource {

    suspend fun getGenresFromServer(genreCategory: GenreCategory): GenreResponse

}