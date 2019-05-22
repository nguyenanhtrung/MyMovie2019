package com.example.mymovie2019.di.module

import android.content.Context
import androidx.room.Room
import com.example.mymovie2019.data.local.database.AppDatabase
import com.example.mymovie2019.data.local.database.dao.GenreDao
import com.example.mymovie2019.data.local.database.dao.MovieDao
import com.example.mymovie2019.di.custom.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context,AppDatabase::class.java,"MovieDatabase").build()
    }

    @Provides
    @Singleton
    fun provideGenreDao(appDatabase: AppDatabase) : GenreDao {
        return appDatabase.genreDao()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase) : MovieDao {
        return appDatabase.movieDao()
    }
}