package ru.mercanie.taskweatherapp.presentation.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WeatherErrorMessages(weatherError: String?, coordinatesError: String?) {
    weatherError?.let { Text("Ошибка: $it", color = MaterialTheme.colorScheme.error) }
    coordinatesError?.let { Text("Ошибка: $it", color = MaterialTheme.colorScheme.error) }
}