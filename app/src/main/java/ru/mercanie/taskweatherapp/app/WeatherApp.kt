package ru.mercanie.taskweatherapp.app

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.mercanie.taskweatherapp.BuildConfig
import ru.mercanie.taskweatherapp.di.networkModule
import ru.mercanie.taskweatherapp.di.repositoryModule
import ru.mercanie.taskweatherapp.di.useCaseModule
import ru.mercanie.taskweatherapp.di.viewModelModule

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val token = BuildConfig.API_TOKEN
        Log.d("TOKEN", token)
        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(networkModule, repositoryModule, viewModelModule, useCaseModule)
        }
    }
}