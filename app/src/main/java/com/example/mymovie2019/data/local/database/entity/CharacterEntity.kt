package com.example.mymovie2019.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private val id: Int,

    @ColumnInfo(name = "name")
    private val name: String,

    @ColumnInfo(name = "movie_id")
    private val movieId: Int,

    @ColumnInfo(name = "cast_id")
    private val castId: Int
)