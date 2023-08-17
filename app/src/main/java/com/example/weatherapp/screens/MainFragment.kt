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
import com.example.weatherapp.network.WeatherApi
import com.example.weatherapp.network.WeatherApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
        recyclerView = binding.cityRecyclerView

        binding.sendRequestButton.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch{
                val q = binding.cityInputEditText.text.toString()
                val days = "5"
                viewModel.getWeather(q, days)
                val model = viewModel.weather.value
                repeatUntil(model)
            }
        }

    }

    private suspend fun repeatUntil(model: Weather?) {
        if (model != null) recyclerView.adapter = DaysAdapter(model, requireContext())
        else {
            delay(100)
            repeatUntil(viewModel.weather.value)
        }
    }

}