package com.example.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemViewBinding
import com.example.weatherapp.network.Weather

class DaysAdapter(
    private val weatherParam: Weather
) : ListAdapter<Weather, DaysAdapter.DaysViewHolder>(DiffCallback) {

    class DaysViewHolder(
        private var binding: ItemViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather, position: Int) {
            binding.conditionIconImageView.setImageResource(weather.forecast.forecastday[position].day.condition.icon.toInt())
            binding.conditionTextView.text = weather.forecast.forecastday[position].day.condition.text
            binding.avgTempTextView.text = weather.forecast.forecastday[position].day.avgtemp_c.toString()
            binding.maxWindKphTextView.text = weather.forecast.forecastday[position].day.maxwind_kph.toString()
            binding.avgHumidityTextView.text = weather.forecast.forecastday[position].day.avghumidity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        return DaysViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bind(weatherParam, position)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

    }
}