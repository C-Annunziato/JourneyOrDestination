package com.example.journeyordestination.model.Api.ApiResponse

data class MapDataResponse(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String


)
//    : List<MapDataResponse> {
//
//
//    fun flatten(): List<MapDataResponse> {
//        val flatpack = mutableListOf<MapDataResponse>()
//        routes?.let { flatpack.add(MapDataResponse(routes)) }
//        return flatpack
//    }
//
//}