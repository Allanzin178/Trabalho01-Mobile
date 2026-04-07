package com.ucbestudoalln.trabalho01_mobile.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ucbestudoalln.trabalho01_mobile.ViewModel.WeatherViewModel
import com.ucbestudoalln.trabalho01_mobile.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
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
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnBuscar.isEnabled = !isLoading
        }
    }

    private fun setupListeners() {
        binding.btnBuscar.setOnClickListener {
            val cep = binding.campoCep.text.toString()
            if (cep.length == 8) {
                viewModel.searchWeather(cep)
            } else {
                binding.campoCep.error = "CEP inválido"
            }
        }

        binding.btnVerHistorico.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}
