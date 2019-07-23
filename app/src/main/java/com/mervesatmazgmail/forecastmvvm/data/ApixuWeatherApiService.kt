package com.mervesatmazgmail.forecastmvvm.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mervesatmazgmail.forecastmvvm.data.db.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY="d8bbd95f66dd4de692370601192307"

// http://api.apixu.com/v1/current.json?key=d8bbd95f66dd4de692370601192307&q=Ankara&lang=tr

interface ApixuWeatherApiService {
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang")languageCode:String="tr"
    ):Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke(): ApixuWeatherApiService{
           val requestInterceptor=Interceptor {chain ->
               val url= chain.request()
                   .url().newBuilder()
                   .addQueryParameter("key", API_KEY)
                   .build()
                   val request=chain.request()
                       .newBuilder()
                       .url(url)
                       .build()
               return@Interceptor chain.proceed(request)


           }
            val okHttpClient=OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }

    }

}