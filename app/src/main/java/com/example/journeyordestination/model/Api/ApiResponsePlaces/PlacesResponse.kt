package com.example.journeyordestination.model.Api.ApiResponsePlaces

data class PlacesResponse(
    val predictions: List<Prediction>,
    val status: String
)