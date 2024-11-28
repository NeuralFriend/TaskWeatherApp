package ru.mercanie.taskweatherapp.domain.repository

import ru.mercanie.taskweatherapp.data.models.weather.WeatherData
import ru.mercanie.taskweatherapp.data.models.weather.WeatherForecast
import ru.mercanie.taskweatherapp.data.remote.utils.ResponseStatus

interface WeatherRepository {
    suspend fun getWeatherData(
        lat: Double?, lon: Double?
    ): ResponseStatus<WeatherData>

    suspend fun getWeatherForecast(
        forecast: Boolean,
        lat: Double?, lon: Double?
    ): ResponseStatus<WeatherForecast>
}