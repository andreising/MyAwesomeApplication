package com.andreising.myawesomeapplication.data.retrofit.instance

import com.andreising.myawesomeapplication.data.retrofit.common.Constants
import com.andreising.myawesomeapplication.data.retrofit.remote.BurgerAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "X-RapidAPI-Key",
                        "2b8bf5d3c4msh5a4540730fc2954p165a8djsn38aff88cb668"
                    )
                    .addHeader("X-RapidAPI-Host", "burgers-hub.p.rapidapi.com")
                    .build()
                chain.proceed(request)
            }
            .build())
    .build()
    .create(BurgerAPI::class.java)