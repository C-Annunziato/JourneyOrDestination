package com.example.journeyordestination.model.Api

import com.example.journeyordestination.model.Api.ApiResponseDirections.MapDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DirectionsApi {

    @GET("maps/api/directions/json?")

    fun getDirectionsCall(
        @Query("origin") origin_id: String?,
        @Query("destination") destination_id: String?,
        @Query("key") apiKey: String,
    ): Call<MapDataResponse>

}