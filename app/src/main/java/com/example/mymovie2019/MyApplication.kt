package com.example.mymovie2019

import android.app.Application
import android.os.StrictMode
import android.util.Log
import com.amplitude.api.Amplitude
import com.example.mymovie2019.di.component.AppComponent
import com.example.mymovie2019.di.component.DaggerAppComponent
import com.example.mymovie2019.di.module.AppModule
import com.example.mymovie2019.di.module.DatabaseModule
import com.example.mymovie2019.di.module.NetworkModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
import com.flurry.android.FlurryAgent



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
        FlurryAgent.Builder()
            .withLogEnabled(true)
            .withLogLevel(Log.VERBOSE)
            .build(this, "VRHW6RW76JGMC7Q63PJF")

        Amplitude.getInstance().initialize(this, "9fac6a79e8ce7a5a14be6ee54b2c3280").enableForegroundTracking(this)
        Amplitude.getInstance().userId = "1102"

        setupLogging()
        setupStrictMode()
        appComponent.inject(this)
    }

//    private fun setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectNetwork()
                .detectDiskReads()
                .detectDiskWrites()
                .penaltyLog()
                .build()
            )
        }
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}