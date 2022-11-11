package com.lydian.journeyordestination.model.Api.ApiResponseDirections

data class Duration(
    val text: String,
    val value: Int,
    val id: GeocodedWaypoint
)