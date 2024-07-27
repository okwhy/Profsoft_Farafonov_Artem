package com.farf.networking.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farf.networking.presentation.model.Weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farf.networking.data.api.ApiClient
import com.farf.networking.presentation.mapper.WeatherMapper
import kotlinx.coroutines.launch


class WeatherViewModel : ViewModel() {
    private val _state = MutableLiveData<Weather>()
    val state: LiveData<Weather> get() = _state
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    private val apiService = ApiClient.getApiService()

    fun fetchData(lat: Float, lon: Float) {
        viewModelScope.launch {
            runCatching {
                apiService.getData(lat, lon)
            }.onSuccess { response ->
                _state.postValue(WeatherMapper.toWeather(response))
            }.onFailure { exception ->
                _error.postValue(exception.message)
            }
        }
    }
}