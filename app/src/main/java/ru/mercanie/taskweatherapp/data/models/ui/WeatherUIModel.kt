package ru.mercanie.taskweatherapp.data.models.ui

import ru.mercanie.taskweatherapp.data.models.weather.WeatherItem

data class WeatherUIModel(
    val date: String,
    val temperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val iconDescr: String
)

fun WeatherItem.toWeatherUIModel(): WeatherUIModel {
    return WeatherUIModel(
        date = dt.toFormattedDate(null),
        temperature = "${main.temp.toInt()}",
        maxTemperature = "${main.tempMax.toInt()}",
        minTemperature = "${main.tempMin.toInt()}",
        iconDescr = "${weather.first().description}}}"
    )
}