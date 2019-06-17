package com.example.mymovie2019.di.component

import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.di.module.*
import com.example.mymovie2019.ui.castdetail.CastDetailActivity
import com.example.mymovie2019.ui.casts.CastsFragment
import com.example.mymovie2019.ui.listmovie.ListMovieFragment
import com.example.mymovie2019.ui.main.MainActivity
import com.example.mymovie2019.ui.moviedetail.MovieDetailActivity
import com.example.mymovie2019.ui.movies.MoviesFragment
import com.example.mymovie2019.ui.seemoremovie.SeeMoreMovieFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class,
    AppModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    RepositoryModule::class])
interface AppComponent {

    fun inject(myApplication: MyApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(movieDetailActivity: MovieDetailActivity)

    fun inject(moviesFragment: MoviesFragment)

    fun inject(castsFragment: CastsFragment)

    fun inject(castDetailActivity: CastDetailActivity)

    fun inject(listMovieFragment: ListMovieFragment)

    fun inject(fragment: SeeMoreMovieFragment)

}