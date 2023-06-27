package com.example.openweathermap

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.openweathermap.Retrofit.JSON_API
import com.example.openweathermap.subclasses.Weather_Now
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Forecast_Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

    }

    private fun getApi(): Call<Weather_Now> {
        val retrofit =
            Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(JSON_API::class.java).forecast()
    }

}