package com.ucbestudoalln.trabalho01_mobile.Model

import com.google.gson.annotations.SerializedName

// Geocoding Models
data class GeocodingResponse(
    val results: List<GeocodingResult>?
)

data class GeocodingResult(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val admin1: String? // State
)

// Weather Models
data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather
)

data class CurrentWeather(
    val temperature: Double,
    val windspeed: Double,
    val weathercode: Int,
    val time: String
)

// Search History Model
data class WeatherHistory(
    val cep: String,
    val city: String,
    val temperature: Double,
    val timestamp: Long = System.currentTimeMillis()
)
