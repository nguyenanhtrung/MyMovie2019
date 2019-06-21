package com.example.mymovie2019.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genre")
data class GenreEntity(

    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "type")
    val type: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "primary_id")
    var primaryId: Long = 0

    @ColumnInfo(name = "movie_detail_id")
    var movieDetailId: Int = 0

    @ColumnInfo(name = "tv_show_id")
    var tvShowId: Int = 0

}