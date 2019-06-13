package com.example.mymovie2019.di.module

import com.example.mymovie2019.data.local.datasource.cast.CastLocalDataSource
import com.example.mymovie2019.data.local.datasource.cast.CastLocalDataSourceImp
import com.example.mymovie2019.data.local.datasource.character.CharacterLocalDataSource
import com.example.mymovie2019.data.local.datasource.character.CharacterLocalDataSourceImp
import com.example.mymovie2019.data.local.datasource.genre.GenreLocalDataSource
import com.example.mymovie2019.data.local.datasource.genre.GenreLocalDataSourceImp
import com.example.mymovie2019.data.local.datasource.movie.MovieLocalDataSource
import com.example.mymovie2019.data.local.datasource.movie.MovieLocalDataSourceImp
import com.example.mymovie2019.data.local.datasource.moviedetail.MovieDetailLocalDataSource
import com.example.mymovie2019.data.local.datasource.moviedetail.MovieDetailLocalDataSourceImp
import com.example.mymovie2019.data.remote.datasource.cast.CastRemoteDataSource
import com.example.mymovie2019.data.remote.datasource.cast.CastRemoteDataSourceImp
import com.example.mymovie2019.data.remote.datasource.genre.GenreRemoteDataSource
import com.example.mymovie2019.data.remote.datasource.genre.GenreRemoteDataSourceImp
import com.example.mymovie2019.data.remote.datasource.movie.MovieRemoteDataSource
import com.example.mymovie2019.data.remote.datasource.movie.MovieRemoteDataSourceImp
import com.example.mymovie2019.data.remote.datasource.moviedetail.MovieDetailRemoteDataSource
import com.example.mymovie2019.data.remote.datasource.moviedetail.MovieDetailRemoteDataSourceImp
import com.example.mymovie2019.data.repository.cast.CastRepository
import com.example.mymovie2019.data.repository.cast.CastRepositoryImp
import com.example.mymovie2019.data.repository.character.CharacterRepository
import com.example.mymovie2019.data.repository.character.CharacterRepositoryImp
import com.example.mymovie2019.data.repository.genre.GenreRepository
import com.example.mymovie2019.data.repository.genre.GenreRepositoryImp
import com.example.mymovie2019.data.repository.movie.MovieRepository
import com.example.mymovie2019.data.repository.movie.MovieRepositoryImp
import com.example.mymovie2019.data.repository.moviedetail.MovieDetailRepository
import com.example.mymovie2019.data.repository.moviedetail.MovieDetailRepositoryImp
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

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(movieRemoteDataSourceImp: MovieRemoteDataSourceImp): MovieRemoteDataSource {
        return movieRemoteDataSourceImp
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(movieLocalDataSourceImp: MovieLocalDataSourceImp): MovieLocalDataSource {
        return movieLocalDataSourceImp
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieRepositoryImp: MovieRepositoryImp): MovieRepository {
        return movieRepositoryImp
    }

    @Provides
    @Singleton
    fun provideCastRemoteDataSource(castRemoteDataSourceImp: CastRemoteDataSourceImp): CastRemoteDataSource {
        return castRemoteDataSourceImp
    }

    @Provides
    @Singleton
    fun provideCastLocalDataSource(castLocalDataSourceImp: CastLocalDataSourceImp): CastLocalDataSource {
        return castLocalDataSourceImp
    }

    @Provides
    @Singleton
    fun provideCastRepository(castRepository: CastRepositoryImp): CastRepository {
        return castRepository
    }

    @Provides
    @Singleton
    fun provideMovieDetailLocalDataSource(movieDetailLocalDataSource: MovieDetailLocalDataSourceImp): MovieDetailLocalDataSource {
        return movieDetailLocalDataSource
    }

    @Provides
    @Singleton
    fun provideMovieDetailRemoteDataSource(movieRemoteDataSourceImp: MovieDetailRemoteDataSourceImp): MovieDetailRemoteDataSource {
        return movieRemoteDataSourceImp
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(movieDetailRepository: MovieDetailRepositoryImp): MovieDetailRepository {
        return movieDetailRepository
    }

    @Provides
    @Singleton
    fun provideCharacterLocalDataSource(characterLocalDataSourceImp: CharacterLocalDataSourceImp): CharacterLocalDataSource {
        return characterLocalDataSourceImp
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(characterRepositoryImp: CharacterRepositoryImp): CharacterRepository {
        return characterRepositoryImp
    }

}