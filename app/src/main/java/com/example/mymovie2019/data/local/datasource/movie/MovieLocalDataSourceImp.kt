package com.example.mymovie2019.data.local.datasource.movie

import com.example.mymovie2019.data.local.model.ItemType
import com.example.mymovie2019.data.local.model.MovieItem
import com.example.mymovie2019.data.local.model.MovieType.*
import com.example.mymovie2019.data.local.model.MoviesVerticalItem
import javax.inject.Inject

class MovieLocalDataSourceImp @Inject constructor() : MovieLocalDataSource {


    override fun getMoviesTypeVerticalItems(): MutableList<MoviesVerticalItem> {
        val movieTypes = mutableListOf<MoviesVerticalItem>()
        movieTypes += MoviesVerticalItem(POPULAR.ordinal, "Popular Movies", mutableListOf(MovieItem(itemType = ItemType.ListLoading)))
        movieTypes += MoviesVerticalItem(UPCOMING.ordinal, "Upcoming Movies", mutableListOf(MovieItem(itemType = ItemType.ListLoading)))
        movieTypes += MoviesVerticalItem(TOP_RATED.ordinal, "TopRated Movies", mutableListOf(MovieItem(itemType = ItemType.ListLoading)))
        return movieTypes
    }
}