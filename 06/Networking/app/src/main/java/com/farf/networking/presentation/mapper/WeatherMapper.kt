package com.farf.networking.presentation.mapper

import com.farf.networking.data.model.WeatherDto
import com.farf.networking.presentation.model.Weather
import kotlin.math.roundToInt

object WeatherMapper {
    private fun toDirection(deg: Int): String {
        return when (deg) {
            in 0..11, in 349..360 -> "N"
            in 12..33 -> "NNE"
            in 34..56 -> "NE"
            in 57..78 -> "ENE"
            in 79..101 -> "E"
            in 102..123 -> "ESE"
            in 124..146 -> "SE"
            in 147..168 -> "SSE"
            in 169..191 -> "S"
            in 192..213 -> "SSW"
            in 214..236 -> "SW"
            in 237..258 -> "WSW"
            in 259..281 -> "W"
            in 282..303 -> "WNW"
            in 304..326 -> "NW"
            in 327..348 -> "NNW"
            else -> "Unknown"
        }
    }

    fun toWeather(weatherDto: WeatherDto): Weather {
        return Weather(
            city = weatherDto.name,
            minTemp = "%d °C".format(weatherDto.main.temp_min.roundToInt()),
            maxTemp = "%d °C".format(weatherDto.main.temp_max.roundToInt()),
            weather = weatherDto.weather.first().description,
            windSpeed = "%.2f m/s".format(weatherDto.wind.speed),
            temp = "%d °C".format(weatherDto.main.temp.roundToInt()),
            feelsTemp = "%d °C".format(weatherDto.main.feels_like.roundToInt()),
            visibility = "%.2f km".format(weatherDto.visibility / 1000.0),
            gust = "%.2f m/s".format(weatherDto.wind.gust),
            pressure = "${weatherDto.main.pressure} hPa",
            direction = toDirection(weatherDto.wind.deg),
            humidity = "${weatherDto.main.humidity} %"
        )
    }
}