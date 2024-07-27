package com.farf.networking.presentation.model


data class Weather(
    val city: String,
    val weather: String,
    val temp: String,
    val feelsTemp: String,
    val minTemp: String,
    val maxTemp: String,
    val pressure: String,
    val visibility: String,
    val windSpeed: String,
    val gust: String,
    val direction: String,
    val humidity:String
)