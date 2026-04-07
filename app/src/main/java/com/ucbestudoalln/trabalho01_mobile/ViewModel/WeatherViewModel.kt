package com.ucbestudoalln.trabalho01_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucbestudoalln.trabalho01_mobile.Model.GeocodingResult
import com.ucbestudoalln.trabalho01_mobile.Model.WeatherHistory
import com.ucbestudoalln.trabalho01_mobile.Model.WeatherRepository
import com.ucbestudoalln.trabalho01_mobile.Model.WeatherResponse
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _weatherData = MutableLiveData<Pair<GeocodingResult, WeatherResponse>?>()
    val weatherData: LiveData<Pair<GeocodingResult, WeatherResponse>?> = _weatherData

    private val _history = MutableLiveData<List<WeatherHistory>>()
    val history: LiveData<List<WeatherHistory>> = _history

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun searchWeather(cep: String) {
        viewModelScope.launch {
            _loading.value = true
            val result = repository.getWeatherByCep(cep)
            _weatherData.value = result
            _loading.value = false
            refreshHistory()
        }
    }

    fun refreshHistory() {
        _history.value = WeatherRepository.getHistory()
    }
}
