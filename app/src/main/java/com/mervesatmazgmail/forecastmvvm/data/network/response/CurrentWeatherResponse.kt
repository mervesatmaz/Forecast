package com.mervesatmazgmail.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.mervesatmazgmail.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.mervesatmazgmail.forecastmvvm.data.db.entity.Location


data class CurrentWeatherResponse(

    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)