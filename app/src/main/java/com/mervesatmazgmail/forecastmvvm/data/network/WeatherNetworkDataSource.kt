package com.mervesatmazgmail.forecastmvvm.data.network

import android.location.Location
import android.telephony.cdma.CdmaCellLocation
import androidx.lifecycle.LiveData
import com.mervesatmazgmail.forecastmvvm.data.network.response.CurrentWeatherResponse


 interface WeatherNetworkDataSource {
    val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
    suspend fun fetchCurrentWeather (
        location: String,
        languageCode: String
    )
}