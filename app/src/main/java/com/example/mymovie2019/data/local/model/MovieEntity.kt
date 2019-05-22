package com.example.mymovie2019.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieEntity(

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String? = "",

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "type")
     val type: String,

    @ColumnInfo(name = "page")
     val page: Int

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "entity_id")
    var entityId: Int = 0
}