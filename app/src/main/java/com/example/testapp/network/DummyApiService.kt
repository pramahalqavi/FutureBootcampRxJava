package com.example.testapp.network

import android.util.Log
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DummyApiService {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun getInstance(): Retrofit {
            val longging = Interceptor {
                val request = it.request()
                Log.d("okhttp", "okhttp--->" + request.url().toString())
                it.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .addInterceptor(longging)
                .build()

            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient).build()
        }

        fun getDummyApi(): DummyApi {
            return getInstance().create(DummyApi::class.java)
        }
    }
}