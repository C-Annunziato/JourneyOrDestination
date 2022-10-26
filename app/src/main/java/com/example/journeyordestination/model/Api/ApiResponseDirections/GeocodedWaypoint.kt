package com.example.journeyordestination.model.Api.ApiResponse

data class GeocodedWaypoint(
    val geocoder_status: String,
    val place_id: String,
    val types: List<String>
)