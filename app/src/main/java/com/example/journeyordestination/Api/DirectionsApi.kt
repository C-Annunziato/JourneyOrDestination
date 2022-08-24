package com.example.journeyordestination.Api

import com.example.journeyordestination.ApiResponse.MapDataList
import com.example.journeyordestination.Const.Constants
import retrofit2.Response
import retrofit2.http.GET

interface DirectionsApi {


    val apiKey: String
        get() = Constants.API_KEY


    //perform get request and get response on another thread
    @GET("maps/api/directions/json?origin=Toronto&destination=Montreal&key=<apiKey>")
    suspend fun getDirections(): Response<MapDataList>

}