package com.example.mymovie2019.data.remote.response

import android.os.Parcelable
import com.example.mymovie2019.data.local.model.CastTransferContract
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    @Json(name = "cast_id")
    override val id: Int = 0,
    @Json(name = "character")
    val character: String = "",
    @Json(name = "credit_id")
    val creditId: String = "",
    @Json(name = "gender")
    val gender: Int = 0,
    @Json(name = "id")
    val orderId: Int = 0,
    @Json(name = "name")
    override val name: String = "",
    @Json(name = "order")
    val order: Int = 0,
    @Json(name = "profile_path")
    override val imageUrl: String? = ""
): Parcelable, CastTransferContract