package ru.mercanie.taskweatherapp.data.repository

import ru.mercanie.taskweatherapp.data.models.weather.WeatherData
import ru.mercanie.taskweatherapp.data.models.weather.WeatherForecast
import ru.mercanie.taskweatherapp.data.remote.WeatherApi
import ru.mercanie.taskweatherapp.data.remote.utils.ResponseStatus
import ru.mercanie.taskweatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double?, lon: Double?): ResponseStatus<WeatherData> {
        return try {
            ResponseStatus.Success(
                data = api.getCurrentDatWeather(
                    lat = lat,
                    lon = lon
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseStatus.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    override suspend fun getWeatherForecast(
        forecast: Boolean,
        lat: Double?,
        lon: Double?
    ): ResponseStatus<WeatherForecast> {
        return try {
            ResponseStatus.Success(
                data = api.getWeatherForecast(
                    lat = lat,
                    lon = lon
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseStatus.Error(e.message ?: "Неизвестная ошибка")
        }
    }
}