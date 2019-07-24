package com.mervesatmazgmail.forecastmvvm.data.network

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mervesatmazgmail.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.mervesatmazgmail.forecastmvvm.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService

) : WeatherNetworkDataSource {
    private val _downloadCurrentWeather= MutableLiveData<CurrentWeatherResponse>()

    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() =_downloadCurrentWeather
    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
try {
    val fetchCurrentWeather= apixuWeatherApiService
        .getCurrentWeather(location,languageCode).await()
    _downloadCurrentWeather.postValue(fetchCurrentWeather)
    }
catch (e: NoConnectivityException)
{
    Log.e("Bağlantı", "İnternet Yok ", e)
}

    }
}