package com.example.openweathermap.Retrofit

import com.example.openweathermap.subclasses.Forecast
import retrofit2.Call
import com.example.openweathermap.subclasses.Weather_Now
import retrofit2.http.GET


interface JSON_API {
    @GET("data/2.5/weather?lat=59.8944&lon=30.2642&appid=f77ad5eac18fb91df2e01d1330c24363&units=metric&lang=ru")
    fun posts(): Call<Weather_Now>

    @GET("data/2.5/forecast?lat=59.8944&lon=30.2642&appid=f77ad5eac18fb91df2e01d1330c24363&units=metric")
    fun forecast(): Call<Forecast>
}
