package ru.mercanie.taskweatherapp.di

import org.koin.dsl.module
import ru.mercanie.taskweatherapp.data.repository.CityCoordinatesRepositoryImpl
import ru.mercanie.taskweatherapp.data.repository.WeatherRepositoryImpl
import ru.mercanie.taskweatherapp.domain.repository.CityCoordinatesRepository
import ru.mercanie.taskweatherapp.domain.repository.WeatherRepository

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<CityCoordinatesRepository> {
        CityCoordinatesRepositoryImpl(api = get())
    }
}