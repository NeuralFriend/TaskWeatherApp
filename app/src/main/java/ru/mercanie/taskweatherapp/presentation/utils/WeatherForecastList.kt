package ru.mercanie.taskweatherapp.presentation.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mercanie.taskweatherapp.data.models.ui.toWeatherUIModel
import ru.mercanie.taskweatherapp.data.models.weather.WeatherItem

@Composable
fun WeatherForecastList(
    title: String,
    forecastData: List<WeatherItem>?
) {
    forecastData?.takeIf { it.isNotEmpty() }?.let { forecast ->
        Column {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(forecast) { item ->
                    WeatherForecastItem(item.toWeatherUIModel())
                }
            }
        }
    }
}