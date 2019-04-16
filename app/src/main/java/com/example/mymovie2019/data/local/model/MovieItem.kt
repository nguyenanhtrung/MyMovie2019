package com.example.mymovie2019.data.local.model

data class MovieItem(
        override var id: Int = -1,
        override var name: String = "",
        override var releaseDate: String = "",
        override var rating: Double = 0.0,
        override var imageUrl: String? = "",
        var itemType: ItemType = ItemType.Normal
) : MovieTransferContract {
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

