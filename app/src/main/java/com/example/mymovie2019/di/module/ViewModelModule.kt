package com.example.mymovie2019.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovie2019.di.custom.ViewModelKey
import com.example.mymovie2019.ui.castdetail.CastDetailViewModel
import com.example.mymovie2019.ui.casts.CastsViewModel
import com.example.mymovie2019.ui.listmovie.ListMovieViewModel
import com.example.mymovie2019.ui.main.MainViewModel
import com.example.mymovie2019.ui.moviedetail.MovieDetailViewModel
import com.example.mymovie2019.ui.movies.MoviesViewModel
import com.example.mymovie2019.utils.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CastsViewModel::class)
    abstract fun bindCastsViewModel(castsViewModel: CastsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CastDetailViewModel::class)
    abstract fun bindCastDetailViewModel(castDetailViewModel: CastDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListMovieViewModel::class)
    abstract fun bindListViewViewModel(listMovieViewModel: ListMovieViewModel): ViewModel


}