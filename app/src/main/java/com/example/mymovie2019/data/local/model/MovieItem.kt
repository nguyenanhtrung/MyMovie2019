package com.example.mymovie2019.data.local.model

data class MovieItem(
        var id: Int = -1,
        var name: String = "",
        var releaseDate: String = "",
        var rating: Double = 0.0,
        var imageUrl: String = "",
        var itemType: ItemType = ItemType.Normal
) {
    constructor(movieItem: MovieItem) : this(movieItem.id, movieItem.name,
                                        movieItem.releaseDate, movieItem.rating,
                                        movieItem.imageUrl, movieItem.itemType) {
        id = movieItem.id
        name = movieItem.name
        releaseDate = movieItem.releaseDate
        rating = movieItem.rating
        imageUrl = movieItem.imageUrl
        itemType = movieItem.itemType

    }
}

