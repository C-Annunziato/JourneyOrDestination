package com.example.journeyordestination.model.Api

import com.example.journeyordestination.model.Api.ApiResponse.MapData
import retrofit2.Response
import retrofit2.http.GET

interface DirectionsApi {

//
//    val apiKey: String
//        get() = Constants.API_KEY


    //perform get request and get response on another thread
    @GET("maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyCPbwgNc-DuUcavE85xwxdIZszafM_9w1c")
    suspend fun getDirections(): Response<MapData>

}