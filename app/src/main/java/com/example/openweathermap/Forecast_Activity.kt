package com.example.openweathermap

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.openweathermap.Retrofit.JSON_API
import com.example.openweathermap.subclasses.Forecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class Forecast_Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        val call = getApi()
        var forecast: Forecast? = null
        val backButton : ImageButton = findViewById(R.id.ForecastBackButton)
        val forecastTextView: TextView = findViewById(R.id.ForecastTextView)
        call.enqueue(object : Callback<Forecast> {
            override fun onResponse(call: Call<Forecast> , response: Response<Forecast>) {
                forecast = response.body()!!
                output(forecast!!, forecastTextView)
            }
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Toast.makeText(applicationContext, applicationContext.getString(R.string.Error), Toast.LENGTH_LONG).show()
            }
        })

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getApi(): Call<Forecast> {
        val retrofit =
            Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(JSON_API::class.java).forecast()
    }
    fun output(forecast: Forecast, forecastTextView: TextView) {
        val builder = StringBuilder()
        for(fore in forecast.list){
            builder.append("  " + convert(fore.dt_txt) + " : ")
                .append(fore.main.temp.roundToInt().toString() + (applicationContext.getString(R.string.celsius)) + "\n")
        }
        forecastTextView.text = builder
    }

    fun convert(date : String): String {
        val output: String = SimpleDateFormat("dd.MM.yyyy HH:mm").format(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date))
        return output
    }


}