package com.example.weatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.weatherapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("forecast.json?")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("aqi") aqi: String,
        @Query("days") days: String,
        @Query("alerts") alerts: String
    ): Weather
}
object WeatherApi {
    val retrofitService: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}