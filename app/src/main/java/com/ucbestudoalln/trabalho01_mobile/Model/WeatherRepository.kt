package com.ucbestudoalln.trabalho01_mobile.Model

import com.ucbestudoalln.trabalho01_mobile.Services.ApiClient
import com.ucbestudoalln.trabalho01_mobile.Services.WeatherApiClient

class WeatherRepository {
    private val cepService = ApiClient.service
    private val geocodingService = WeatherApiClient.geocodingService
    private val weatherService = WeatherApiClient.weatherService

    // In-memory history for now
    companion object {
        private val history = mutableListOf<WeatherHistory>()
        fun getHistory() = history.toList()
        fun addHistory(item: WeatherHistory) {
            history.add(0, item)
        }
    }

    suspend fun getWeatherByCep(cep: String): Pair<GeocodingResult, WeatherResponse>? {
        return try {
            // 1. Get city name from CEP
            val cepData = cepService.buscarCep(cep)
            val cityName = cepData.localidade

            // 2. Get coordinates from city name
            val geoResponse = geocodingService.searchCity(cityName)
            val location = geoResponse.results?.firstOrNull() ?: return null

            // 3. Get weather from coordinates
            val weather = weatherService.getWeather(location.latitude, location.longitude)

            // 4. Save to history
            addHistory(WeatherHistory(cep, location.name, weather.currentWeather.temperature))

            Pair(location, weather)
        } catch (e: Exception) {
            null
        }
    }
}
