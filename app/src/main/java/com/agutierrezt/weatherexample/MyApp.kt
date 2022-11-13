package com.agutierrezt.weatherexample

import android.app.Application
import com.agutierrezt.weatherexample.di.dataSourceModule
import com.agutierrezt.weatherexample.di.repoModule
import com.agutierrezt.weatherexample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(dataSourceModule, repoModule, viewModelModule))
        }
    }
}