package com.agutierrezt.weatherexample.di

import com.agutierrezt.weatherexample.data.viewmodels.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        WeatherViewModel(get())
    }
}