package ru.mercanie.taskweatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mercanie.taskweatherapp.BuildConfig
import ru.mercanie.taskweatherapp.data.models.geo.GeoData

interface GeoApi {

    @GET("geo/1.0/direct")
    suspend fun getGeoData(
        @Query("q") city: String?,
        @Query("limit") limit: Int = 2,
        @Query("appid") appid: String = BuildConfig.API_TOKEN
    ): GeoData

    @GET("geo/1.0/reverse")
    suspend fun getCityByCoords(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("limit") limit: Int = 2,
        @Query("appid") appid: String = BuildConfig.API_TOKEN
    ): GeoData
}