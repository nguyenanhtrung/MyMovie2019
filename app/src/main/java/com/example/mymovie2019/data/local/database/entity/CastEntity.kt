package com.example.mymovie2019.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Cast")
data class CastEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "profile_path")
    val imagePath: String

)