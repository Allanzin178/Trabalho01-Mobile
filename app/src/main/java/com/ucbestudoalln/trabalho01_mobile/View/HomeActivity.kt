package com.ucbestudoalln.trabalho01_mobile.View

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.ucbestudoalln.trabalho01_mobile.R
import com.ucbestudoalln.trabalho01_mobile.Services.ApiClient
import com.ucbestudoalln.trabalho01_mobile.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuscar.setOnClickListener {
            val cep = binding.campoCep.text.toString()

            lifecycleScope.launch {
                try {
                    val endereco = ApiClient.service.buscarCep(cep)

                    binding.displayLocalidade.text = endereco.localidade
                } catch (e: Exception) {
                    Toast.makeText(this@HomeActivity, "Erro ao buscar CEP", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}