package com.agutierrezt.weatherexample.di

import com.agutierrezt.weatherexample.data.api.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataSourceModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    val logging  = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder().readTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
