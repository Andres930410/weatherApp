package com.agutierrezt.weatherexample.data.models

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("latitude")
    val lat: Double,
    @SerializedName("longitude")
    val lng: Double,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("hourly")
    val hourly: ForecastHourly,
    @SerializedName("hourly_units")
    val unit: ForecastUnit,
    @SerializedName("current_weather")
    val currentWeather: Weather?
)