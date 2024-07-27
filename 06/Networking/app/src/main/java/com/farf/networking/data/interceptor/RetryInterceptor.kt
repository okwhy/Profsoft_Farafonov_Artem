package com.farf.networking.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetryInterceptor(private val maxRetry: Int) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        var exception: Exception? = null
        var attempt = 0

        while (attempt < maxRetry) {
            try {
                response = chain.proceed(chain.request())
                if (response.isSuccessful) {
                    return response
                }
            } catch (e: Exception) {
                exception = e
            }
            attempt++
        }

        exception?.let { throw it }
        return response ?: throw IOException("Failed to connect")
    }
}