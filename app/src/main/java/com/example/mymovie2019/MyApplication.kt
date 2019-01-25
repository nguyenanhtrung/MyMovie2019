package com.example.mymovie2019

import android.app.Application
import com.example.mymovie2019.di.component.AppComponent
import com.example.mymovie2019.di.component.DaggerAppComponent
import com.example.mymovie2019.di.module.AppModule
import com.example.mymovie2019.di.module.DatabaseModule
import com.example.mymovie2019.di.module.NetworkModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .networkModule(NetworkModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        setupLeakCanary()
        setupLogging()
        appComponent.inject(this)
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}