package com.example.journeyordestination.model.Api.ApiResponse

data class MapData(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)