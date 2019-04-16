package com.example.mymovie2019.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CastTransfer(var id: Int = -1, var name: String = "", var imageUrl: String? = ""): Parcelable {

    constructor(castContract: CastTransferContract) : this() {
        id = castContract.id
        name = castContract.name
        imageUrl = castContract.imageUrl
    }
}