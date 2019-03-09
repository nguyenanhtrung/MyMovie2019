package com.example.mymovie2019.data.local.datasource.movie

import com.example.mymovie2019.data.local.model.MoviesVerticalItem

interface MovieLocalDataSource {

    fun getMoviesTypeVerticalItems(): MutableList<MoviesVerticalItem>
}