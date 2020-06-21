package br.com.homartapp.data.remote

class LocationResponse(
    val listLocations: ArrayList<Location>
)

data class Location(
    val name: String,
    val review: Double,
    val type: String,
    val id: Long
)