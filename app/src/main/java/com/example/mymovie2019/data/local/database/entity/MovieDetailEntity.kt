package com.example.mymovie2019.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieDetail")
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "budget")
    val budget: Long,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "runtime")
    val runtime: Int,

    @ColumnInfo(name = "premiere")
    val premiere: String,

    @ColumnInfo(name = "revenue")
    val revenue: Long,

    @ColumnInfo(name = "home_page")
    val homePage: String

)