package ru.mercanie.taskweatherapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.mercanie.taskweatherapp.presentation.screens.weather.WeatherViewModel


val viewModelModule = module {
    viewModel { WeatherViewModel(get(), get()) }
}
