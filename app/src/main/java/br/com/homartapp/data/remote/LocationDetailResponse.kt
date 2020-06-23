package br.com.homartapp.data.remote

import br.com.homartapp.data.model.Day

class LocationDetailResponse(
    val name: String,
    val review: Double,
    val type: String,
    val id: Int,
    val about: String,
    val schedule: ArrayList<Schedule>,
    val phone: String,
    val adress: String
)

data class Schedule(
    val sunday: Day,
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