package ru.mercanie.taskweatherapp.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.mercanie.taskweatherapp.data.models.weather.WeatherData

@Composable
fun WeatherCurrent(weatherData: WeatherData?) {
    weatherData?.let {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleLarge("${it.main.temp?.toInt()}°C")
            Image(
                painter = painterResource(id = getWeatherImage(it.weather.first().description)),
                contentDescription = "Weather Condition",
                modifier = Modifier.size(128.dp)
            )
        }
    }
}
