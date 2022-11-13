package com.agutierrezt.weatherexample.di

import com.agutierrezt.weatherexample.data.repositories.WeatherRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        WeatherRepository(get())
    }
}