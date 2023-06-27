package com.example.openweathermap

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.openweathermap.Retrofit.JSON_API
import com.example.openweathermap.subclasses.Weather_Now
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tempTextView: TextView = findViewById(R.id.temp_view)
        val description: TextView = findViewById(R.id.textView_desc)
        val picture: WebView = findViewById(R.id.icon)
        val mainTextView: TextView = findViewById(R.id.textView_main)
        val details: Button = findViewById(R.id.Details_Button)
        val forecast: Button = findViewById(R.id.Forecast_button)
        val call = getApi()
        var weather: Weather_Now? = null
        call.enqueue(object : Callback<Weather_Now> {
            override fun onResponse(call: Call<Weather_Now>, response: Response<Weather_Now>) {
                weather = response.body()!!
                output(weather!!, tempTextView, description, picture, mainTextView)
            }

            override fun onFailure(call: Call<Weather_Now>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_SHORT).show()
                ///!!!!!!
            }
        })
        details.setOnClickListener {
            val randomIntent = Intent(this, Details_Activity::class.java)
            randomIntent.putExtra("visibility", weather?.visibility.toString())
            randomIntent.putExtra("wind", weather?.wind!!.deg)
            randomIntent.putExtra("clouds", weather?.clouds!!.all.toString())
            randomIntent.putExtra("sunset", weather?.sys!!.sunset)
            randomIntent.putExtra("sunrise", weather?.sys!!.sunrise)
            randomIntent.putExtra("temp_min",weather?.main!!.temp_min.toString())
            randomIntent.putExtra("temp_max",weather?.main!!.temp_max.toString())
            startActivity(randomIntent)
        }

        forecast.setOnClickListener {
            val randomIntent = Intent(this, Forecast_Activity::class.java)
            startActivity(randomIntent)
        }
    }

    fun output(weather: Weather_Now, temp_textView : TextView, description : TextView, picture : WebView,mainTextView : TextView) {
        var tempStr = ""
        val builder = StringBuilder()
        if (weather.main.temp > 0) tempStr = applicationContext.getString(R.string.plus)
        tempStr += ((weather.main.temp).roundToInt().toString()) + (applicationContext.getString(R.string.celsius))
        temp_textView.text = tempStr
        description.text = weather.weather[0].description
        mainTextView.text = builder
            .append("\n" + applicationContext.getString(R.string.feelsLike) + ": ")
            .append(weather.main.feels_like.roundToInt().toString())
            .append((applicationContext.getString(R.string.celsius) + "\n"))
            .append("\n" + applicationContext.getString(R.string.humidity)+ ": ")
            .append(weather.main.humidity.roundToInt().toString())
            .append(" " + applicationContext.getString(R.string.procent) + "\n" + "\n")
            .append(applicationContext.getString(R.string.pressure) + ": ")
            .append((weather.main.pressure/1.333).roundToInt().toString() + " мм рт.ст. \n")
            .append("\n" + applicationContext.getString(R.string.wind_speed)+ ": ")
            .append(weather.wind.speed + " м/с \n")
        picture.loadUrl("https://openweathermap.org/img/wn/" + weather.weather[0].icon + "@2x.png")
    }

    private fun getApi(): Call<Weather_Now> {
        val retrofit =
            Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(JSON_API::class.java).posts()
    }

}