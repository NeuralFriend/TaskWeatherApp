package ru.mercanie.taskweatherapp.presentation.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mercanie.taskweatherapp.data.models.ui.WeatherUIModel

@Composable
fun WeatherForecastItem(item: WeatherUIModel) {
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(modifier = Modifier.padding(horizontal = 4.dp)) {
                TextSmall(item.date)
               ImageCondition(item.iconDescr)
            }
            TextMedium("~${item.temperature}°C")
        }
    }
}