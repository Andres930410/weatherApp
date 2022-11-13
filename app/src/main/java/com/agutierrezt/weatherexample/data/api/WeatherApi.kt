package com.agutierrezt.weatherexample.data.api

import com.agutierrezt.weatherexample.data.models.Forecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun forecast(@Query("latitude") lat: Double, @Query("longitude") lng: Double, @Query("hourly",
        encoded = true
    ) hourly: String, @Query("current_weather") currentWeather: Boolean): Response<Forecast>

    @GET("forecast")
    suspend fun historical(@Query("latitude") lat: Double, @Query("longitude") lng: Double,
                           @Query("start_date") startDate: String, @Query("end_date") endDate: String, @Query("hourly",
        encoded = true
    ) hourly: String, @Query("current_weather") currentWeather: Boolean): Response<Forecast>
}