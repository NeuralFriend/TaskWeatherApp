package ru.mercanie.taskweatherapp.presentation.screens.weather

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.mercanie.taskweatherapp.data.models.ui.toFormattedDate
import ru.mercanie.taskweatherapp.data.remote.utils.Constants
import ru.mercanie.taskweatherapp.presentation.utils.CityInputRow
import ru.mercanie.taskweatherapp.presentation.utils.HourlyToday
import ru.mercanie.taskweatherapp.presentation.utils.Space
import ru.mercanie.taskweatherapp.presentation.utils.WeatherCurrent
import ru.mercanie.taskweatherapp.presentation.utils.WeatherErrorMessages
import ru.mercanie.taskweatherapp.presentation.utils.WeatherForecastList

@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel = koinViewModel(),
    context: Context = LocalContext.current
) {
    val state by weatherViewModel.state.collectAsState()
    var city by remember { mutableStateOf(state.cityName.orEmpty()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CityInputRow(city, onCityChange = { city = it }) {
            if (city.isNotBlank()) {
                weatherViewModel.getCoordinatesByCityName(city, null, null)
            } else {
                Toast.makeText(context, "Укажите город", Toast.LENGTH_SHORT).show()
            }
        }
        Space()

        if (state.isLoadingWeather || state.isLoadingCoordinates) {
            CircularProgressIndicator()
        } else {
            WeatherCurrent(state.weatherData)
            Space()
            state.dayWeatherForecastData?.let { forecast ->
                if (forecast.isNotEmpty()) {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(forecast) { item ->
                            HourlyToday(
                                hour = item.dt.toFormattedDate("HH:mm"),
                                temp = "${item.main.temp.toInt()}°C"
                            )
                        }
                    }
                }
            }
            Space()

            WeatherForecastList(
                title = "Прогноз на неделю",
                forecastData = state.weekWeatherForecastData?.let(weatherViewModel::getWeekForecast)
            )
        }
        WeatherErrorMessages(state.weatherError, state.coordinatesError)
    }
    LaunchedEffect(Unit) {
        val lat = Constants.getCurrentLat(context)
        val lon = Constants.getCurrentLon(context)
        if (lat != null && lon != null) {
            weatherViewModel.getCoordinatesByCityName(null, lat, lon)
            weatherViewModel.getWeatherData(lat, lon)
            weatherViewModel.getWeatherForecast(lat, lon)
        }
        else{
            weatherViewModel.getCoordinatesByCityName("Moscow", lat, lon)
        }
    }

    LaunchedEffect(state.cityName) {
        city = state.cityName.orEmpty()
    }
}