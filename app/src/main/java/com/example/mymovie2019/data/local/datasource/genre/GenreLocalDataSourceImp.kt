package com.example.mymovie2019.data.local.datasource.genre

import com.example.mymovie2019.data.local.database.dao.GenreDao
import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.Genre
import javax.inject.Inject

class GenreLocalDataSourceImp @Inject constructor(private val genreDao: GenreDao) : GenreLocalDataSource {

    override fun saveGenres(genres: List<GenreLocal>) {
        return genreDao.insertDatas(genres)
    }

    override fun getGenres(genreType: GenreCategory): MutableList<GenreLocal> {
        return genreDao.getGenres(genreType.categoryName)
    }

    override fun countMovieGenres(typeName: String): Long {
        return genreDao.countMovieGenres(typeName)
    }

    override fun getGenreNames(genres: List<Genre>?): String {
        if (genres == null) {
            return ""
        }
        val genreNames = genres.map {
            it.name
        }
        return genreNames.joinToString()
    }
}