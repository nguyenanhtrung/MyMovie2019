package com.example.mymovie2019.ui.main

import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.remote.response.Genre
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.utils.AppKey
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val genreRepository: GenreRepository) : BaseViewModel() {

    fun loadGenresFromServer() {
        val handlerException = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }

        launch(handlerException) {
                val genresMovieResponse = genreRepository.getGenresMovieFromServer(AppKey.API_KEY).await()
                val genresTvShowResponse = genreRepository.getGenresTvShowFromServer(AppKey.API_KEY).await()
                val genresMovie = genresMovieResponse.genres
                val genresTvShow = genresTvShowResponse.genres

                withContext(Dispatchers.IO) {
                    saveGenresToLocal(genresMovie,GenreCategory.MOVIE)
                }
                withContext(Dispatchers.IO) {
                    saveGenresToLocal(genresTvShow,GenreCategory.TV_SHOW)
                }

        }
    }

    private fun saveGenresToLocal(genres: List<Genre>, genreCategory: GenreCategory) {
        if (!genres.isEmpty()) {
            val genreLocals = genres.map {
                GenreLocal(it.id.toLong(),it.name,genreCategory.categoryName)
            }
            genreRepository.saveGenres(genreLocals)
        }
    }
}