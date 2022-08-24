package com.example.journeyordestination.Api

import com.example.journeyordestination.Const.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val apiKey: String
        get() = Constants.API_KEY

    //create retrofit and create service
    val api: DirectionsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionsApi::class.java)
    }
}