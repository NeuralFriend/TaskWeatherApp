package ru.mercanie.taskweatherapp.data.repository

import ru.mercanie.taskweatherapp.data.models.geo.GeoData
import ru.mercanie.taskweatherapp.data.remote.GeoApi
import ru.mercanie.taskweatherapp.data.remote.utils.ResponseStatus
import ru.mercanie.taskweatherapp.domain.repository.CityCoordinatesRepository

class CityCoordinatesRepositoryImpl(
    private val api: GeoApi
) : CityCoordinatesRepository {

    override suspend fun getCityCoordinates(
        city: String?,
        lat: Double?,
        lon: Double?
    ): ResponseStatus<GeoData> {
        return try {
            if(city != null){
                ResponseStatus.Success(
                    data = api.getGeoData(
                        city = city,
                    )
                )
            }
            else if(lat != null && lon != null) {
                ResponseStatus.Success(
                    data = api.getCityByCoords(
                        lat = lat,
                        lon = lon
                    )
                )
            }
            else {
                ResponseStatus.Success(
                    data = api.getGeoData(
                        city = "Moscow"
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseStatus.Error(e.message ?: "An unknown error occurred.")
        }
    }
}