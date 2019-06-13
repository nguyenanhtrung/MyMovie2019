package com.example.mymovie2019.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "cast_id")
    @ForeignKey(entity = CastEntity::class,parentColumns = ["id"], childColumns = ["cast_id"])
    val castId: Int
)