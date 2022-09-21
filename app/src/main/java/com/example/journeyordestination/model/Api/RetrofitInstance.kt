package com.example.journeyordestination.model.Api

import com.example.journeyordestination.viewmodel.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //create retrofit and create service
    val api: DirectionsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionsApi::class.java)
    }

    fun call() = api.getDirectionsCall()
}