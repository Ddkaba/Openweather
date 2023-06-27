package com.example.openweathermap.subclasses

import kotlin.collections.List

class Weather_Now (val weather: List<Weather>, val main: Main, val visibility: String, val wind: Wind, val clouds: Clouds,
                   val sys: Sys)  {
}