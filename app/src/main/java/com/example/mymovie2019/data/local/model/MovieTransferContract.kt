package com.example.mymovie2019.data.local.model

interface MovieTransferContract {

    var id: Int
    var name: String
    var releaseDate: String
    var rating: Double
    var imageUrl: String?
}