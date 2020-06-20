package br.com.homartapp.data.remote

class LocationDetailResponse(
    val name: String,
    val review: Long,
    val type: String,
    val id: Long,
    val about: String,
    val schedule: Schedule,
    val phone: String,
    val adress: String
)

data class Schedule(
    val monday: Day,
    val tuesday: Day,
    val wednesday: Day,
    val thursday: Day,
    val friday: Day,
    val saturday: Day
)

data class Day (
    val open: String,
    val close: String
)