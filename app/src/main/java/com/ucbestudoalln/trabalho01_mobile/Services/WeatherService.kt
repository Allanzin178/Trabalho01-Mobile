package com.ucbestudoalln.trabalho01_mobile.Services

import com.ucbestudoalln.trabalho01_mobile.Model.GeocodingResponse
import com.ucbestudoalln.trabalho01_mobile.Model.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
    @GET("v1/search")
    suspend fun searchCity(
        @Query("name") name: String,
        @Query("count") count: Int = 1,
        @Query("language") language: String = "pt",
        @Query("format") format: String = "json"
    ): GeocodingResponse
}

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true
    ): WeatherResponse
}

object WeatherApiClient {
    private val geocodingRetrofit = Retrofit.Builder()
        .baseUrl("https://geocoding-api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherRetrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val geocodingService: GeocodingService = geocodingRetrofit.create(GeocodingService::class.java)
    val weatherService: WeatherApiService = weatherRetrofit.create(WeatherApiService::class.java)
}
