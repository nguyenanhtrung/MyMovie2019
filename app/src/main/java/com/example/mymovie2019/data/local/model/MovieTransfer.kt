package com.example.mymovie2019.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieTransfer(
        var id: Int,
        var name: String,
        var imagePath: String?,
        var rating: Double,
        var releaseDate: String
) : Parcelable {

    constructor(movieTransferContract: MovieTransferContract) : this(
            movieTransferContract.id,
            movieTransferContract.name,
            movieTransferContract.imageUrl,
            movieTransferContract.rating,
            movieTransferContract.releaseDate
    ) {
        id = movieTransferContract.id
        name = movieTransferContract.name
        imagePath = movieTransferContract.imageUrl
        rating = movieTransferContract.rating
        releaseDate = movieTransferContract.releaseDate
    }
}