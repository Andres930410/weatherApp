package com.agutierrezt.weatherexample.data.models

import com.google.gson.annotations.SerializedName

data class ForecastHourly(
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("temperature_2m")
    val temperature: List<Double>
)