package com.example.journeyordestination.model.Api

import com.example.journeyordestination.model.Api.ApiResponse.MapDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface DirectionsApi {

//
//    val apiKey: String
//        get() = Constants.API_KEY


    @GET("maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyCPbwgNc-DuUcavE85xwxdIZszafM_9w1c")
     fun getDirectionsCall(): Call<MapDataResponse>

}