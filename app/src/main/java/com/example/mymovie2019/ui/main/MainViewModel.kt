package com.example.mymovie2019.ui.main

import com.example.mymovie2019.data.local.model.GenreCategory
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.domains.genres.GetGenresUseCase
import com.example.mymovie2019.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val genreRepository: GenreRepository) : BaseViewModel() {

    private val getMovieGenresUseCase by lazy {
        GetGenresUseCase(this, genreRepository, this)
    }

    fun loadGenres() {
        getMovieGenresUseCase.invoke(GenreCategory.MOVIE) {
        }
    }

}