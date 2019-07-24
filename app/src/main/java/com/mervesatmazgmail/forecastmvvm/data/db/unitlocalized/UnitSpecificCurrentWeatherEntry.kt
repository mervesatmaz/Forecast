package com.mervesatmazgmail.forecastmvvm.data.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {

    val temperature: Double
    val conditionText : String
    val conditionIconUrl: String
    val winSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature:Double
    val visibilityDistance: Double

}