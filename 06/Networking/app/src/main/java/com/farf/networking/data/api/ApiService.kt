package com.farf.networking.data.api

import com.farf.networking.data.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/data/2.5/weather")
    suspend fun getData(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float
    ): WeatherDto
}