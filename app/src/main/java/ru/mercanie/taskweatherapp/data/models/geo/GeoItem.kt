package ru.mercanie.taskweatherapp.data.models.geo

import com.google.gson.annotations.SerializedName

data class GeoItem(
    val country: String,
    val lat: Double,
    @SerializedName("local_names")
    val localNames: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)