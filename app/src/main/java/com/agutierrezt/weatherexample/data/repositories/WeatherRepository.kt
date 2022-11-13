package com.agutierrezt.weatherexample.data.repositories

import com.agutierrezt.weatherexample.data.api.WeatherApi
import com.agutierrezt.weatherexample.data.models.Forecast

class WeatherRepository(private val api: WeatherApi) {
    suspend fun forecast(lat: Double, lng: Double): Forecast {
        val response = api.forecast(lat, lng, "temperature_2m", true)
        if(response.isSuccessful) return response.body()!!
        throw Exception("We can't get the temperature")
    }

    suspend fun historical(lat: Double, lng: Double, startDate: String, endDate: String): Forecast {
        val response = api.historical(lat, lng, startDate, endDate, "temperature_2m", true)
        if(response.isSuccessful) return response.body()!!
        throw Exception("We can't get the temperature")
    }
}

