package com.mervesatmazgmail.forecastmvvm

import android.app.Application
import com.mervesatmazgmail.forecastmvvm.data.db.ForecastDataBase
import com.mervesatmazgmail.forecastmvvm.data.network.*
import com.mervesatmazgmail.forecastmvvm.data.repository.ForecastRepository
import com.mervesatmazgmail.forecastmvvm.data.repository.ForecastRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ForecastApplication : Application(),KodeinAware{
    override val  kodein= Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
bind() from singleton { ForecastDataBase(instance() ) }
        bind() from singleton { instance<ForecastDataBase> ().currentWeatherDao()}
bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance() ) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance()) }



    }
}