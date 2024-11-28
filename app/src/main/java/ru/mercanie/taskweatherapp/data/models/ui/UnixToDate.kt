package ru.mercanie.taskweatherapp.data.models.ui

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(formatDate: String?): String {
    val date = Date(this * 1000)
    val format = SimpleDateFormat(formatDate?: "dd.MM", Locale.getDefault())
    return format.format(date)
}