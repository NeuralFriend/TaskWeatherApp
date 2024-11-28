package ru.mercanie.taskweatherapp.domain.use_case

import ru.mercanie.taskweatherapp.data.models.weather.WeatherData
import ru.mercanie.taskweatherapp.data.models.weather.WeatherForecast
import ru.mercanie.taskweatherapp.data.remote.utils.ResponseStatus
import ru.mercanie.taskweatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double?, lon: Double?): ResponseStatus<WeatherData> {
        return weatherRepository.getWeatherData(lat, lon)
    }

    suspend operator fun invoke(forecast: Boolean, lat: Double?, lon: Double?): ResponseStatus<WeatherForecast> {
        return weatherRepository.getWeatherForecast(forecast, lat, lon)
    }
}