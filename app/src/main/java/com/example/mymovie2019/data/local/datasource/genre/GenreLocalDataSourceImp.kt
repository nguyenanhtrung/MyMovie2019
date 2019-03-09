package com.example.mymovie2019.data.local.datasource.genre

import androidx.lifecycle.LiveData
import com.example.mymovie2019.data.local.database.dao.GenreDao
import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.GenreCategory
import javax.inject.Inject

class GenreLocalDataSourceImp @Inject constructor(private val genreDao: GenreDao) : GenreLocalDataSource {

    override fun saveGenres(genres: List<GenreLocal>) {
        return genreDao.insertDatas(genres)
    }

    override fun getGenres(genreCategory: GenreCategory, offSet: Int, limit: Int): LiveData<List<GenreLocal>> {
        return genreDao.getGenres(genreCategory.categoryName,offSet, limit)
    }

    override fun countMovieGenres(typeName: String): Long {
        return genreDao.countMovieGenres(typeName)
    }
}