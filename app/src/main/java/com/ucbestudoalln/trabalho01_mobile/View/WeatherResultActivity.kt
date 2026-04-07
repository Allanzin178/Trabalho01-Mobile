package com.ucbestudoalln.trabalho01_mobile.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ucbestudoalln.trabalho01_mobile.databinding.ActivityWeatherResultBinding

class WeatherResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cityName = intent.getStringExtra("CITY_NAME") ?: ""
        val state = intent.getStringExtra("STATE") ?: ""
        val temp = intent.getDoubleExtra("TEMP", 0.0)
        val wind = intent.getDoubleExtra("WIND", 0.0)

        binding.txtCityName.text = cityName
        binding.txtState.text = state
        binding.txtTemperature.text = "${temp}°C"
        binding.txtWind.text = "Vento: ${wind} km/h"

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
