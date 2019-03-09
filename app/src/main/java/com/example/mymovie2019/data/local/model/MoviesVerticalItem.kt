package com.example.mymovie2019.data.local.model

data class MoviesVerticalItem(
        var id: Int = 0,
        var title: String = "",
        var movieItems: MutableList<MovieItem> = mutableListOf()
) {
    constructor(moviesVerticalItem: MoviesVerticalItem): this(moviesVerticalItem.id,moviesVerticalItem.title, moviesVerticalItem.movieItems) {
        id = moviesVerticalItem.id
        title = moviesVerticalItem.title
        movieItems = mutableListOf()
        for (index in 0 until moviesVerticalItem.movieItems.size) {
            val movieItemCopy = MovieItem(moviesVerticalItem.movieItems[index])
            movieItems.add(movieItemCopy)
        }
    }
}