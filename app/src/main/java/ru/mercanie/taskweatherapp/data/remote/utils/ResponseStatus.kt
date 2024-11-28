package ru.mercanie.taskweatherapp.data.remote.utils


sealed class ResponseStatus<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = false
) {
    class Success<T>(data: T?) : ResponseStatus<T>(data)
    class Error<T>(message: String, data: T? = null) : ResponseStatus<T>(data, message)
}