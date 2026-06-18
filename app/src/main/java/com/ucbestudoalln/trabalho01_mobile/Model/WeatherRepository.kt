package com.ucbestudoalln.trabalho01_mobile.Model

import android.content.Context
import com.ucbestudoalln.trabalho01_mobile.Services.ApiClient
import com.ucbestudoalln.trabalho01_mobile.Services.WeatherApiClient
import java.util.Locale

class WeatherRepository(context: Context) {
    private val cepService = ApiClient.service
    private val geocodingService = WeatherApiClient.geocodingService
    private val weatherService = WeatherApiClient.weatherService
    private val db = WeatherDatabase(context)

    suspend fun getHistory() = db.getAllHistory()

    suspend fun clearHistory() = db.deleteAll()

    suspend fun getWeatherByCep(cep: String): Pair<GeocodingResult, WeatherResponse>? {
        return try {
            val cepData = cepService.buscarCep(cep)
            val cityName = cepData.localidade

            val geoResponse = geocodingService.searchCity(cityName)
            val location = geoResponse.results?.firstOrNull() ?: return null

            val weather = weatherService.getWeather(location.latitude, location.longitude)

            db.insertHistory(WeatherHistory(cep = cep, city = location.name, temperature = weather.currentWeather.temperature))

            Pair(location, weather)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getWeatherByCoordinates(lat: Double, lon: Double, cityName: String? = null): Pair<GeocodingResult, WeatherResponse>? {
        return try {
            val weather = weatherService.getWeather(lat, lon)
            
            val location = GeocodingResult(
                name = cityName ?: "Coordenadas",
                latitude = lat,
                longitude = lon,
                admin1 = String.format(Locale.getDefault(), "%.4f, %.4f", lat, lon)
            )

            db.insertHistory(WeatherHistory(cep = "Mapa", city = cityName ?: "Local via Mapa", temperature = weather.currentWeather.temperature))

            Pair(location, weather)
        } catch (e: Exception) {
            null
        }
    }
}
