package ru.mercanie.taskweatherapp.data.models.weather

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    val dt: Long,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)