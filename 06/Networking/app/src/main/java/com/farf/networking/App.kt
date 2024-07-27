package com.farf.networking

import android.app.Application
import com.farf.networking.data.api.ApiClient

class App : Application() {
    companion object {
        private const val API_KEY = "ac25cc2bb6a5ded56731f607b2847b37"
    }

    override fun onCreate() {
        super.onCreate()
        ApiClient.initialize(this, API_KEY)
    }
}
