package com.example.mymovie2019.data.repository.genre

import com.example.mymovie2019.data.local.datasource.genre.GenreLocalDataSource
import com.example.mymovie2019.data.remote.datasource.genre.GenreRemoteDataSource

interface GenreRepository : GenreRemoteDataSource, GenreLocalDataSource {
}