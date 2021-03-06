package com.example.mymovie2019.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genre")
data class GenreLocal(

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
}