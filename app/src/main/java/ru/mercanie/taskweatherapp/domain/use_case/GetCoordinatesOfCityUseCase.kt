package ru.mercanie.taskweatherapp.domain.use_case

import ru.mercanie.taskweatherapp.data.models.geo.GeoData
import ru.mercanie.taskweatherapp.data.remote.utils.ResponseStatus
import ru.mercanie.taskweatherapp.domain.repository.CityCoordinatesRepository

class GetCoordinatesOfCityUseCase(
    private val cityCoordinatesRepository: CityCoordinatesRepository
) {
    suspend operator fun invoke(city: String?, lat: Double?, lon: Double?): ResponseStatus<GeoData> {
        return cityCoordinatesRepository.getCityCoordinates(city, lat, lon)
    }
}