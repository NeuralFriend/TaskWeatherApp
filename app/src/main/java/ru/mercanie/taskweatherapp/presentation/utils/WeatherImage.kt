package ru.mercanie.taskweatherapp.presentation.utils

import androidx.annotation.DrawableRes
import ru.mercanie.taskweatherapp.R

@DrawableRes
fun getWeatherImage(condition: String): Int {
    val lowerCaseCondition = condition.lowercase()
    return when {
        "snow" in lowerCaseCondition -> R.drawable.snow
        "rain" in lowerCaseCondition -> R.drawable.rain
        "clear" in lowerCaseCondition -> R.drawable.sun
        "cloud" in lowerCaseCondition -> R.drawable.sky
        else -> R.drawable.sky
    }
}