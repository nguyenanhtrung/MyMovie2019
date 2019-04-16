package com.example.mymovie2019.ui.main

import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.domains.genres.LoadMovieGenresLocalUseCase
import com.example.mymovie2019.ui.base.BaseViewModel
import com.example.mymovie2019.ui.base.UseCaseHandler
import javax.inject.Inject

class MainViewModel @Inject constructor(private val genreRepository: GenreRepository) : BaseViewModel() {

    private val loadMovieGenresLocalUseCase : LoadMovieGenresLocalUseCase by lazy {
        LoadMovieGenresLocalUseCase(genreRepository,this)
    }


    internal fun loadGenres() {
        loadMovieGenresLocalUseCase.invoke(Unit,object : UseCaseHandler<Unit> {
            override fun onSuccess(result: Unit) = Unit
        })
    }

    override fun onCleared() {
        loadMovieGenresLocalUseCase.cancel()
        super.onCleared()
    }
}