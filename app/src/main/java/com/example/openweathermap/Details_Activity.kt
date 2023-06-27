package com.example.openweathermap

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.Calendar

class Details_Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val details : TextView = findViewById(R.id.DetailsTextView)
        val back: ImageButton = findViewById(R.id.back_imageview)
        val builder = StringBuilder()
        details.text = builder
            .append("\n" + "\n  " + applicationContext.getString(R.string.temp_min)+ ": ")
            .append(intent.getStringExtra("temp_min") + applicationContext.getString(R.string.celsius) + "\n" )
            .append("\n  " + applicationContext.getString(R.string.temp_max)+ ": ")
            .append(intent.getStringExtra("temp_max") + applicationContext.getString(R.string.celsius) + "\n" )
            .append("\n  " + applicationContext.getString(R.string.visibility)+ ": ")
            .append(intent.getStringExtra("visibility") + " м" + "\n" )
            .append("\n  " + applicationContext.getString(R.string.windDegree) + ": ")
            .append(intent.getStringExtra("wind") + applicationContext.getString(R.string.celsius) +"\n")
            .append("\n  " + applicationContext.getString(R.string.clouds)+ ": ")
            .append(intent.getStringExtra("clouds") + " " + applicationContext.getString(R.string.procent) + "\n")
            .append("\n  " + applicationContext.getString(R.string.sunrise)+ ": ")
            .append(convertTime(intent.getLongExtra("sunrise",0)) + "\n")
            .append("\n  " + applicationContext.getString(R.string.sunset)+ ": ")
            .append(convertTime(intent.getLongExtra("sunset",0)))

        back.setOnClickListener{
            onBackPressed()
        }
    }

    fun convertTime(Unix:Long): String {
        val time = java.text.SimpleDateFormat(applicationContext.getString(R.string.format)).format(java.util.Date((Unix) * 1000))
        return time
    }

}
