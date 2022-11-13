package com.agutierrezt.weatherexample.data.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("time")
    val time: String,
    @SerializedName("temperature")
    val temperature: Double
)