package com.example.journeyordestination.model.Api

import com.example.journeyordestination.model.Api.ApiResponseDirections.MapDataResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TAGS = "hello"

object RetrofitInstance {
    val api: DirectionsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionsApi::class.java)
    }

    fun call(originId: String?, destinationId: String?, apiKey: String): Call<MapDataResponse> {

//        Log.i(TAGS, " From: RetrofitInstance - > getDirectionsCallParams $destinationId, $originId")
        return api.getDirectionsCall("place_id:$originId", "place_id:$destinationId", apiKey)
    }
}

