package com.parsdroid.tmdbmovieapp

import android.app.Application
import com.parsdroid.tmdbmovieapp.di.AppComponent
import com.parsdroid.tmdbmovieapp.di.DaggerAppComponent

class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp

        // Reference to the application graph that is used across the whole app
        val appComponent: AppComponent by lazy { DaggerAppComponent.create() }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}