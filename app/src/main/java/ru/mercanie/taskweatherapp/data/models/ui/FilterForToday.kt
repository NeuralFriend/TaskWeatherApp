package ru.mercanie.taskweatherapp.data.models.ui

import ru.mercanie.taskweatherapp.data.models.weather.WeatherItem

fun List<WeatherItem>.filterForToday(): List<WeatherItem> {
    val today = (System.currentTimeMillis() / 1000).toFormattedDate("yyyy-MM-dd")
    val todayForecast: MutableList<WeatherItem> = mutableListOf()
    this.forEach { if (it.dt.toFormattedDate("yyyy-MM-dd") == today) todayForecast.add(it) }
    return todayForecast
}