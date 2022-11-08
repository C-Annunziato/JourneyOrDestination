package com.example.journeyordestination.model.Api.ApiResponseDirections

data class MapDataResponse(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String

)