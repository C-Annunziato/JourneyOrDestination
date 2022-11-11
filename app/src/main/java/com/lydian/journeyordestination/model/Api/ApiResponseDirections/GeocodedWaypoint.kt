package com.lydian.journeyordestination.model.Api.ApiResponseDirections

data class GeocodedWaypoint(
    val geocoder_status: String,
    val place_id: String,
    val types: List<String>
)