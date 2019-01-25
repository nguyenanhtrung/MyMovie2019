package com.example.mymovie2019.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymovie2019.data.local.database.dao.GenreDao
import com.example.mymovie2019.data.local.database.entity.GenreLocal

@Database(entities = [GenreLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
}