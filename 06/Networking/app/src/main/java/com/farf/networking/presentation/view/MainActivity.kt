package com.farf.networking.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.farf.networking.databinding.ActivityMainBinding
import com.farf.networking.presentation.viewmodel.WeatherViewModel
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    companion object {
        private const val LATITUDE = 51.51f
        private const val LONGITUDE = 46.01f
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        viewModel.state.observe(this) { weather ->
            Log.d("TAG", "onCreate: $weather")
            binding.apply {
                tvCityName.text = weather.city
                tvCityName.text = weather.city
                tvCurrentTemp.text = weather.temp
                updateTextView(tvDirection, weather.direction)
                updateTextView(tvWindSpeed, weather.windSpeed)
                updateTextView(tvGust, weather.gust)
                updateTextView(tvVisibility, weather.visibility)
                updateTextView(tvPressure, weather.pressure)
                updateTextView(tvHumidity, weather.humidity)
                updateTextView(tvFeelsLike, weather.feelsTemp, weather.weather)
                updateTextView(tvTempRange, weather.minTemp, weather.maxTemp)
            }
        }
        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.fetchData(LATITUDE, LONGITUDE)
    }

    private fun updateTextView(textView: TextView, vararg newValues: String) {
        val originalText = textView.text.toString()
        val pattern = Pattern.compile("\\(([^)]+)\\)")
        val matcher = pattern.matcher(originalText)
        var i = 0

        val result = StringBuffer()
        while (matcher.find() && i < newValues.size) {
            matcher.appendReplacement(result, newValues[i])
            i++
        }
        matcher.appendTail(result)
        textView.text = result.toString()
    }
}