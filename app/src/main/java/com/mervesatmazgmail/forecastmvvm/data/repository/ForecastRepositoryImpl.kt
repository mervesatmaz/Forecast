package com.mervesatmazgmail.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.mervesatmazgmail.forecastmvvm.data.db.CurrentWeatherDao
import com.mervesatmazgmail.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.mervesatmazgmail.forecastmvvm.data.network.WeatherNetworkDataSource
import com.mervesatmazgmail.forecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {
    init {
        weatherNetworkDataSource.downloadCurrentWeather.observeForever { newCurrentWeather ->

            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
return  withContext(Dispatchers.IO){
    initWeatherData()
    return@withContext if (metric) currentWeatherDao.getWeatherMetric()
    else currentWeatherDao.getWeatherImperial()
}
    }
    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch (Dispatchers.IO){
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }
    private suspend fun initWeatherData(){
if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
    fetchCurrentWeather()
    }
    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
            "Ankara",
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo=ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)

    }
}