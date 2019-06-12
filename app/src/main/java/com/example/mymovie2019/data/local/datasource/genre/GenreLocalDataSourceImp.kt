package com.example.mymovie2019.data.local.datasource.genre

import com.example.mymovie2019.data.local.database.dao.GenreDao
import com.example.mymovie2019.data.local.database.entity.GenreEntity
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.Genre
import javax.inject.Inject

class GenreLocalDataSourceImp @Inject constructor(private val genreDao: GenreDao) : GenreLocalDataSource {


    override fun updateMovieDetailId(movieDetailId: Long, genreIds: List<Long>?) {
        genreIds?.let {
            genreDao.updateMovieDetailId(movieDetailId,it)
        }
    }

    override fun saveGenres(genres: List<GenreEntity>) {
        return genreDao.insertDatas(genres)
    }

    override fun getGenres(genreType: GenreCategory): MutableList<GenreEntity> {
        return genreDao.getGenres(genreType.categoryName)
    }

    override fun countMovieGenres(typeName: String): Long {
        return genreDao.countMovieGenres(typeName)
    }

    override fun getGenreNames(genres: List<Genre>?): String {
        if (genres == null) {
            return ""
        }
        if (genres.size == 1) {
            return genres[0].name
        }

        val genreNames = genres.map {
            it.name
        }
        return genreNames.joinToString()
    }
}