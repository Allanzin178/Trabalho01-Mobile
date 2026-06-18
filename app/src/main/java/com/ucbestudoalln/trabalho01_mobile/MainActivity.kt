package com.ucbestudoalln.trabalho01_mobile

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.ucbestudoalln.trabalho01_mobile.View.HomeActivity
import com.ucbestudoalln.trabalho01_mobile.View.MapActivity
import com.ucbestudoalln.trabalho01_mobile.View.HistoryActivity
import com.ucbestudoalln.trabalho01_mobile.ViewModel.WeatherViewModel
import com.ucbestudoalln.trabalho01_mobile.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val prefs by lazy { getSharedPreferences("settings", Context.MODE_PRIVATE) }
    
    // Sensor variables
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar o tema antes do onCreate
        val isDarkMode = prefs.getBoolean("dark_mode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchTheme.isChecked = isDarkMode

        // Initialize Sensor
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnCep.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.btnGeo.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        binding.btnHistorico.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("dark_mode", isChecked).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    // Sensor implementation
    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH
            if (acceleration > 12) { // Threshold for shake
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastShakeTime > 2000) { // Avoid multiple shakes
                    lastShakeTime = currentTime
                    viewModel.clearHistory()
                    Toast.makeText(this, "Histórico limpo via sensor!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
