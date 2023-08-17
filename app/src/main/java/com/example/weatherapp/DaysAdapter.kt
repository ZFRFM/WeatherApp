package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ItemViewBinding
import com.example.weatherapp.network.Weather

class DaysAdapter(
    private val weatherParam: Weather,
    private val context: Context
) : RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {

    class DaysViewHolder(
        private var binding: ItemViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather, position: Int, context: Context) {
            Glide.with(context)
                .load(weather.forecast.forecastday[position].day.condition.icon)
                .into(binding.conditionIconImageView)
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
        holder.bind(weatherParam, position, context)
    }

    override fun getItemCount() = weatherParam.forecast.forecastday.size

}