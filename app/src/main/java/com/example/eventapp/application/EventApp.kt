package com.example.eventapp.application

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.example.eventapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.eventapp.BuildConfig

class EventApp : Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin(){
        startKoin {
            androidContext(this@EventApp)
            modules(appModule)
        }

    }

}