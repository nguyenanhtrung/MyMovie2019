package com.example.mymovie2019.data.remote.datasource.genre

import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.GenreResponse
import com.example.mymovie2019.data.remote.service.ApiService
import com.example.mymovie2019.utils.AppKey
import javax.inject.Inject

class GenreRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : GenreRemoteDataSource {


    override suspend fun getGenresFromServer(genreCategory: GenreCategory): GenreResponse = when(genreCategory) {
        GenreCategory.MOVIE -> apiService.getGenresOfMovieAsync(AppKey.API_KEY).await()
        GenreCategory.TV_SHOW -> apiService.getGenresOfTvShowAsync(AppKey.API_KEY).await()
    }

}