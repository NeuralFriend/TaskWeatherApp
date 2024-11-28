package ru.mercanie.taskweatherapp.presentation.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HourlyToday(hour: String, temp: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextSmall(hour)
        TextMedium(temp)
    }
}