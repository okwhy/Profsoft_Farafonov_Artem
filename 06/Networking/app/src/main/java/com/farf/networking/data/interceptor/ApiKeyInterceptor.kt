package com.farf.networking.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val interceptedRequest = chain.request()
        val interceptedUrl = interceptedRequest.url
        val editedUrl = interceptedUrl.newBuilder()
            .addQueryParameter("units", "metric")
            .addQueryParameter("appid", apiKey)
            .build()
        val editedRequest = interceptedRequest.newBuilder()
            .url(editedUrl)
            .build()
        return chain.proceed(editedRequest)
    }
}