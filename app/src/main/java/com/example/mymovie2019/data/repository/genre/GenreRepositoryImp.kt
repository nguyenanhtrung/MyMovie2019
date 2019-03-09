package com.example.mymovie2019.data.repository.genre

import androidx.lifecycle.LiveData
import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.datasource.genre.GenreLocalDataSource
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.datasource.genre.GenreRemoteDataSource
import com.example.mymovie2019.data.remote.response.GenreResponse
import javax.inject.Inject

class GenreRepositoryImp @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val genreLocalDataSource: GenreLocalDataSource
) : GenreRepository {

    override fun saveGenres(genres: List<GenreLocal>){
        return genreLocalDataSource.saveGenres(genres)
    }

    override fun getGenres(genreCategory: GenreCategory, offSet: Int, limit: Int): LiveData<List<GenreLocal>> {
        return genreLocalDataSource.getGenres(genreCategory,limit,offSet)
    }

    override fun countMovieGenres(typeName: String): Long = genreLocalDataSource.countMovieGenres(typeName)

    override suspend fun getGenresMovieFromServer(apiKey: String): GenreResponse {
        return genreRemoteDataSource.getGenresMovieFromServer(apiKey)
    }

    override suspend fun getGenresTvShowFromServer(apiKey: String): GenreResponse {
        return genreRemoteDataSource.getGenresTvShowFromServer(apiKey)
    }
}