package ru.mercanie.taskweatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mercanie.taskweatherapp.BuildConfig
import ru.mercanie.taskweatherapp.data.models.weather.WeatherData
import ru.mercanie.taskweatherapp.data.models.weather.WeatherForecast

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentDatWeather(
    @Query("lat") lat: Double?,
    @Query("lon") lon: Double?,
    @Query("units") units: String = "metric",
    @Query("appid") appid: String = BuildConfig.API_TOKEN
    ): WeatherData

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = BuildConfig.API_TOKEN
    ): WeatherForecast
}