package ru.mercanie.taskweatherapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mercanie.taskweatherapp.BuildConfig
import ru.mercanie.taskweatherapp.data.remote.GeoApi
import ru.mercanie.taskweatherapp.data.remote.WeatherApi
import ru.mercanie.taskweatherapp.data.remote.utils.Constants.BASE_URL
import java.util.concurrent.TimeUnit

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
    if (BuildConfig.DEBUG) {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }
    connectTimeout(30, TimeUnit.SECONDS)
    readTimeout(30, TimeUnit.SECONDS)
    writeTimeout(30, TimeUnit.SECONDS)
}.build()

fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

val networkModule = module {
    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(BASE_URL, get(), get()) }
    single { get<Retrofit>().create(WeatherApi::class.java) }
    single { get<Retrofit>().create(GeoApi::class.java) }
}