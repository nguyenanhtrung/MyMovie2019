package com.example.mymovie2019.di.component

import com.example.mymovie2019.MyApplication
import com.example.mymovie2019.di.module.*
import com.example.mymovie2019.ui.main.MainActivity
import com.example.mymovie2019.ui.movies.MoviesFragment
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

    fun inject(moviesFragment: MoviesFragment)
}