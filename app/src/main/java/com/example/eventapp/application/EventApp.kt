package com.example.eventapp.application

import android.app.Application
import com.example.eventapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EventApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EventApp)
            modules(appModule)
        }
    }
}