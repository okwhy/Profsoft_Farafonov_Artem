package com.farf.networking.data.model


data class CoordDto(
    val lat: Float,
    val lon: Float,
)

data class MainDataDto(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)

data class WindDto(
    val speed: Float,
    val deg: Int,
    val gust: Float
)

data class WeatherConditionDto(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherDto(
    val cod: Int,
    val id: Long,
    val timezone: Int,
    val name: String,
    val dt: Long,
    val weather: List<WeatherConditionDto>,
    val main: MainDataDto,
    val coord: CoordDto,
    val wind: WindDto,
    val visibility: Int
)