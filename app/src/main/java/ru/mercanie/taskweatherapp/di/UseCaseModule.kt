package ru.mercanie.taskweatherapp.di

import org.koin.dsl.module
import ru.mercanie.taskweatherapp.domain.use_case.GetCoordinatesOfCityUseCase
import ru.mercanie.taskweatherapp.domain.use_case.GetWeatherUseCase

val useCaseModule = module {
    factory { GetWeatherUseCase(get()) }
    factory { GetCoordinatesOfCityUseCase(get()) }
}