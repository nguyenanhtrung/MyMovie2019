package com.example.mymovie2019.data.repository.genre

import com.example.mymovie2019.data.local.database.entity.GenreEntity
import com.example.mymovie2019.data.local.datasource.genre.GenreLocalDataSource
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.datasource.genre.GenreRemoteDataSource
import com.example.mymovie2019.data.remote.response.Genre
import com.example.mymovie2019.data.remote.response.GenreResponse
import javax.inject.Inject

class GenreRepositoryImp @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val genreLocalDataSource: GenreLocalDataSource
) : GenreRepository {

    override fun updateMovieDetailId(movieDetailId: Long, genreIds: List<Long>?) {
        genreLocalDataSource.updateMovieDetailId(movieDetailId, genreIds)
    }

    override fun saveGenres(genres: List<GenreEntity>){
        return genreLocalDataSource.saveGenres(genres)
    }

    override fun getGenres(genreType: GenreCategory): MutableList<GenreEntity> {
        return genreLocalDataSource.getGenres(genreType)
    }

    override fun countMovieGenres(typeName: String): Long = genreLocalDataSource.countMovieGenres(typeName)

    override suspend fun getGenresFromServer(genreCategory: GenreCategory): GenreResponse = genreRemoteDataSource.getGenresFromServer(genreCategory)

    override fun getGenreNames(genres: List<Genre>?): String {
        return genreLocalDataSource.getGenreNames(genres)
    }
}