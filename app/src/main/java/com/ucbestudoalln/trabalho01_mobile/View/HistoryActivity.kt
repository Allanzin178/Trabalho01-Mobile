package com.ucbestudoalln.trabalho01_mobile.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucbestudoalln.trabalho01_mobile.Model.WeatherHistory
import com.ucbestudoalln.trabalho01_mobile.ViewModel.WeatherViewModel
import com.ucbestudoalln.trabalho01_mobile.databinding.ActivityHistoryBinding
import com.ucbestudoalln.trabalho01_mobile.databinding.ItemHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        
        viewModel.history.observe(this) { history ->
            binding.rvHistory.adapter = HistoryAdapter(history)
        }
        
        viewModel.refreshHistory()
    }
}

class HistoryAdapter(private val history: List<WeatherHistory>) : 
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = history[position]
        holder.binding.txtCity.text = item.city
        holder.binding.txtCep.text = item.cep
        holder.binding.txtTemp.text = "${item.temperature}°C"
    }

    override fun getItemCount() = history.size
}
