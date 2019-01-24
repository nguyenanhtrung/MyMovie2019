package com.example.mymovie2019.di.module

import android.app.Application
import com.example.mymovie2019.di.custom.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application : Application) {

    @Provides
    @ApplicationContext
    fun provideContext() = application.applicationContext
}