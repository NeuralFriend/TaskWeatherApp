package ru.mercanie.taskweatherapp.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ImageCondition(image: String){
    Image(
        painter = painterResource(id = getWeatherImage(image)),
        contentDescription = "weather cond",
        modifier = Modifier
    )
}
