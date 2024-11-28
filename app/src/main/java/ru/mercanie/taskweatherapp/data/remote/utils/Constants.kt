package ru.mercanie.taskweatherapp.data.remote.utils

import android.content.Context

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/"
    const val PREFS_NAME = "task_weather_prefs"
    const val KEY_LAT = "current_lat"
    const val KEY_LON = "current_lon"

    fun getCurrentLat(context: Context): Double? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getFloat(KEY_LAT, Float.NaN).takeIf { !it.isNaN() }?.toDouble()
    }

    fun getCurrentLon(context: Context): Double? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getFloat(KEY_LON, Float.NaN).takeIf { !it.isNaN() }?.toDouble()
    }
}