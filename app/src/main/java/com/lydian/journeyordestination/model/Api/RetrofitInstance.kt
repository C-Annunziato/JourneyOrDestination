package com.lydian.journeyordestination.model.Api

import com.lydian.journeyordestination.model.Api.ApiResponseDirections.MapDataResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: DirectionsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionsApi::class.java)
    }

    fun call(originId: String?, destinationId: String?, apiKey: String): Call<MapDataResponse> {
        return api.getDirectionsCall("place_id:$originId", "place_id:$destinationId", apiKey)
    }
}

