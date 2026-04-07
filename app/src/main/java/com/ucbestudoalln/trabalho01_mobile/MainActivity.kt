package com.ucbestudoalln.trabalho01_mobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ucbestudoalln.trabalho01_mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Criando viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ativando o viewBinding
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}