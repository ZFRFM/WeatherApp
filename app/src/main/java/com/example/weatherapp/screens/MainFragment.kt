package com.example.weatherapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DaysAdapter
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.network.Condition
import com.example.weatherapp.network.Day
import com.example.weatherapp.network.Forecast
import com.example.weatherapp.network.Forecastday
import com.example.weatherapp.network.Weather
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendRequestButton.setOnClickListener {
            viewModel.getWeather()
            recyclerView = binding.cityRecyclerView
            recyclerView.adapter = DaysAdapter(viewModel.weather.value!!)
        }

    }

}