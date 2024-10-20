package com.example.kotlincryptosample

import android.app.Application
import android.util.Log
import com.example.kotlincryptosample.inject.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("App", "onCreate: ")
        startKoin {
            androidContext(this@App)
            androidLogger()

            modules(appModule)
        }

    }
}