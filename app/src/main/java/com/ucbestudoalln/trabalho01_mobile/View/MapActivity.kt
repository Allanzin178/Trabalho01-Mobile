package com.ucbestudoalln.trabalho01_mobile.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ucbestudoalln.trabalho01_mobile.R
import com.ucbestudoalln.trabalho01_mobile.ViewModel.WeatherViewModel
import com.ucbestudoalln.trabalho01_mobile.databinding.ActivityMapBinding
import android.location.Geocoder
import java.util.Locale

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapBinding
    private lateinit var mMap: GoogleMap
    private var selectedLatLng: LatLng? = null
    private var selectedCity: String? = null
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        setupObservers()
        setupListeners()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Configurações do mapa para melhor visualização
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true

        // Brasília - DF (Coordenadas mais centrais)
        val brasilia = LatLng(-15.7942, -47.8822)
        
        // Move a câmera para Brasília com um zoom mais próximo (nível 10)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brasilia, 10f))

        mMap.setOnMapClickListener { latLng ->
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(latLng))
            selectedLatLng = latLng
            
            // Tenta obter o nome da cidade
            try {
                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                selectedCity = addresses?.firstOrNull()?.locality ?: addresses?.firstOrNull()?.subAdminArea
            } catch (e: Exception) {
                selectedCity = null
            }

            val displayText = if (selectedCity != null) {
                "$selectedCity (Lat: %.4f, Lon: %.4f)"
            } else {
                "Lat: %.4f, Lon: %.4f"
            }
            
            binding.txtCoords.text = String.format(Locale.getDefault(), displayText, latLng.latitude, latLng.longitude)
            binding.btnConfirmar.isEnabled = true
        }
    }

    private fun setupObservers() {
        viewModel.weatherData.observe(this) { result ->
            if (result != null) {
                val (location, weather) = result
                val intent = Intent(this, WeatherResultActivity::class.java).apply {
                    putExtra("CITY_NAME", location.name)
                    putExtra("STATE", location.admin1)
                    putExtra("TEMP", weather.currentWeather.temperature)
                    putExtra("WIND", weather.currentWeather.windspeed)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Erro ao buscar dados do clima", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.btnConfirmar.isEnabled = !isLoading
        }
    }

    private fun setupListeners() {
        binding.btnConfirmar.setOnClickListener {
            selectedLatLng?.let {
                viewModel.searchWeatherByCoordinates(it.latitude, it.longitude, selectedCity)
            }
        }
    }
}