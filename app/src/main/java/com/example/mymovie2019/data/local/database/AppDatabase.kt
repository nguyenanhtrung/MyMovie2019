package com.example.mymovie2019.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymovie2019.data.local.database.dao.GenreDao
import com.example.mymovie2019.data.local.database.dao.MovieDao
import com.example.mymovie2019.data.local.database.entity.GenreLocal
import com.example.mymovie2019.data.local.model.MovieEntity

@Database(entities = [GenreLocal::class, MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
}