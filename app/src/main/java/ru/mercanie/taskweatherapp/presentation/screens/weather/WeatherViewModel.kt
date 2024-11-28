package ru.mercanie.taskweatherapp.presentation.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.mercanie.taskweatherapp.data.models.geo.GeoData
import ru.mercanie.taskweatherapp.data.models.ui.filterForToday
import ru.mercanie.taskweatherapp.data.models.ui.toFormattedDate
import ru.mercanie.taskweatherapp.data.models.weather.WeatherData
import ru.mercanie.taskweatherapp.data.models.weather.WeatherItem
import ru.mercanie.taskweatherapp.data.remote.utils.ResponseStatus
import ru.mercanie.taskweatherapp.domain.use_case.GetCoordinatesOfCityUseCase
import ru.mercanie.taskweatherapp.domain.use_case.GetWeatherUseCase

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getCoordinatesOfCityUseCase: GetCoordinatesOfCityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherScreenState())
    val state: StateFlow<WeatherScreenState> = _state

    fun getWeatherData(lat: Double?, lon: Double?) {
        viewModelScope.launch {
            updateState { copy(isLoadingWeather = true) }
            when (val result = getWeatherUseCase(lat, lon)) {
                is ResponseStatus.Success -> {
                    updateState {
                        copy(
                            isLoadingWeather = false,
                            weatherData = result.data,
                            weatherError = null
                        )
                    }
                }

                is ResponseStatus.Error -> {
                    updateState {
                        copy(
                            isLoadingWeather = false,
                            weatherError = result.message
                        )
                    }
                }
            }
        }
    }

    fun getWeatherForecast(lat: Double?, lon: Double?) {
        viewModelScope.launch {
            updateState { copy(isLoadingWeather = true) }
            when (val result = getWeatherUseCase(true, lat, lon)) {
                is ResponseStatus.Success -> {
                    updateState {
                        val weekForecastData = result.data?.let { getWeekForecast(it.list) }
                        val dayForecastData = result.data?.let { it.list.filterForToday() }
                        copy(
                            isLoadingWeather = false,
                            weekWeatherForecastData = weekForecastData,
                            dayWeatherForecastData = dayForecastData,
                            weatherError = null
                        )
                    }
                }

                is ResponseStatus.Error -> {
                    updateState {
                        copy(
                            isLoadingWeather = false,
                            weatherError = result.message
                        )
                    }
                }
            }
        }
    }

    fun getCoordinatesByCityName(city: String?, lat: Double?, lon: Double?) {
        viewModelScope.launch {
            updateState { copy(isLoadingCoordinates = true) }
            when (val result = getCoordinatesOfCityUseCase(city, lat, lon)) {
                is ResponseStatus.Success -> {
                    val coordinatesList = result.data
                    if (!coordinatesList.isNullOrEmpty()) {
                        val newLat = coordinatesList.first().lat
                        val newLon = coordinatesList.first().lon
                        updateState {
                            copy(
                                isLoadingCoordinates = false,
                                cityName = coordinatesList?.first()?.localNames?.ru
                                    ?: "Неизвестный город",
                                coordinatesError = null

                            )
                        }
                        getWeatherData(newLat, newLon)
                        getWeatherForecast(newLat, newLon)
                    } else {
                        updateState {
                            copy(
                                isLoadingCoordinates = false,
                                coordinatesError = "Город не найден"
                            )
                        }
                    }
                }

                is ResponseStatus.Error -> {
                    updateState {
                        copy(
                            isLoadingCoordinates = false,
                            coordinatesError = result.message
                        )
                    }
                }
            }
        }
    }

    fun getWeekForecast(data: List<WeatherItem>): List<WeatherItem> {
        return data
            .groupBy { it.dt.toFormattedDate(null) }
            .map { (_, items) -> items.first() }
    }

    private fun updateState(update: WeatherScreenState.() -> WeatherScreenState) {
        _state.value = _state.value.update()
    }

    data class WeatherScreenState(
        val isLoadingWeather: Boolean = false,
        val weatherData: WeatherData? = null,
        val weekWeatherForecastData: List<WeatherItem>? = null,
        val dayWeatherForecastData: List<WeatherItem>? = null,
        val weatherError: String? = null,

        val isLoadingCoordinates: Boolean = false,
        val coordinatesData: GeoData? = null,
        val coordinatesError: String? = null,
        val cityName: String? = null
    )
}