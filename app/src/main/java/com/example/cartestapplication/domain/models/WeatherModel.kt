package com.example.cartestapplication.domain.models

data class WeatherModel(
    val locationName: String,
    val weatherTemperature: Int? = null,
    val range: Int? = null
)