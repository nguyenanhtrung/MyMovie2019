package com.example.mymovie2019.di.module

import com.example.mymovie2019.data.local.datasource.genre.GenreLocalDataSource
import com.example.mymovie2019.data.local.datasource.genre.GenreLocalDataSourceImp
import com.example.mymovie2019.data.remote.datasource.genre.GenreRemoteDataSource
import com.example.mymovie2019.data.remote.datasource.genre.GenreRemoteDataSourceImp
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.data.repository.genre.GenreRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGenreRemoteDataSource(genreRemoteDataSource: GenreRemoteDataSourceImp): GenreRemoteDataSource {
        return genreRemoteDataSource
    }

    @Provides
    @Singleton
    fun provideGenreLocalDataSource(genreLocalDataSource: GenreLocalDataSourceImp): GenreLocalDataSource {
        return genreLocalDataSource
    }

    @Provides
    @Singleton
    fun provideGenreRepository(genreRepository: GenreRepositoryImp): GenreRepository {
        return genreRepository
    }
}