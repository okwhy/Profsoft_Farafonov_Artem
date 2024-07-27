package com.farf.networking.data.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.farf.networking.data.interceptor.ApiKeyInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://api.openweathermap.org"
    private const val READ_TIMEOUT_IN_SECONDS = 30L
    private const val CONNECTION_TIMEOUT_IN_SECONDS = 30L
    private const val MAX_RETRY = 3
    private lateinit var apiService: ApiService

    fun initialize(context: Context, apiKey: String) {
        val moshi: Moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(ApiKeyInterceptor(apiKey))
//            .addInterceptor(RetryInterceptor(maxRetry = MAX_RETRY))
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getApiService(): ApiService = apiService
}