package com.example.weatherapp.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.Weather
import com.example.weatherapp.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel: ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    suspend fun getWeather(
        q: String,
        days: String
    ) {
        viewModelScope.launch {
            _weather.value = WeatherApi.retrofitService.getWeather(
                "6c40f3b21c3642b4b8261922231308",
                q = q,
                "no",
                days = days,
                "no"
            )
        }
    }

}