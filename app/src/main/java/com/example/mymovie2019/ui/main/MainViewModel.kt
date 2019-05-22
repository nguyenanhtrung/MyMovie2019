package com.example.mymovie2019.ui.main

import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.domains.genres.GetMovieGenresUseCase
import com.example.mymovie2019.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val genreRepository: GenreRepository) : BaseViewModel() {

    private val getMovieGenresUseCase by lazy {
        GetMovieGenresUseCase(this, genreRepository, this)
    }

    fun loadGenres() {
        getMovieGenresUseCase.invoke(Unit) {
        }
    }

}