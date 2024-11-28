package ru.mercanie.taskweatherapp.domain.repository

import ru.mercanie.taskweatherapp.data.models.geo.GeoData
import ru.mercanie.taskweatherapp.data.remote.utils.ResponseStatus

interface CityCoordinatesRepository {
    suspend fun getCityCoordinates(
       city: String?,
       lat: Double?,
       lon: Double?,
    ): ResponseStatus<GeoData>
}