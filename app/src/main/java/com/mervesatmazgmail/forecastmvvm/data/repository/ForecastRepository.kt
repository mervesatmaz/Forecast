package com.mervesatmazgmail.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.mervesatmazgmail.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric:Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>
}