package com.agutierrezt.weatherexample.data.models

import com.google.gson.annotations.SerializedName

data class ForecastUnit(
    @SerializedName("temperature_2m")
    val unit: String
)