package com.example.mymovie2019.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymovie2019.data.local.database.dao.*
import com.example.mymovie2019.data.local.database.entity.*

@Database(entities = [GenreEntity::class,
    MovieEntity::class,
    MovieDetailEntity::class,
    CastEntity::class,
    CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun characterDao(): CharacterDao
    abstract fun castDao(): CastDao
}