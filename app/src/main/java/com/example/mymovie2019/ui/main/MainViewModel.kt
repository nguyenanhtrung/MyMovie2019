package com.example.mymovie2019.ui.main

import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.utils.AppKey
import com.example.mymovie2019.utils.AppKey.Companion.EMPTY_COUNT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val genreRepository: GenreRepository) : BaseViewModel() {

    internal fun loadGenres() {
        launch {
            showLoading()
            loadMovieGenres()
            hideLoading()
        }
    }

    private suspend fun loadMovieGenres() {
        val genreCount = withContext(Dispatchers.IO) {
            genreRepository.countMovieGenres(GenreCategory.MOVIE.categoryName)
        }
        if (genreCount == EMPTY_COUNT) {
            val genreResponse = genreRepository.getGenresMovieFromServer(AppKey.API_KEY)
            val genreOriginals = genreResponse.genres
            val genreLocals = genreOriginals.map {
                GenreLocal(it.id.toLong(), it.name, GenreCategory.MOVIE.categoryName)
            }
            saveGenres(genreLocals)
        }
    }

    private suspend fun saveGenres(genreLocals: List<GenreLocal>) {
        withContext(Dispatchers.IO) {
            genreRepository.saveGenres(genreLocals)
        }
        Timber.d("Load successful")
    }

}