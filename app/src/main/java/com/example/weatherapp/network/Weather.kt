package com.example.weatherapp.network

data class Weather (
    val forecast: Forecast
)

data class Forecast (
    val forecastday: List<Forecastday>
)

data class Forecastday (
    val day: Day
)

data class Day (
    val avgtemp_c: Float,
    val maxwind_kph: Float,
    val avghumidity: Float,
    val condition: Condition
)

data class Condition (
    val text: String,
    val icon: String
)