package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class StandingsResponse(
    @SerializedName("table")
    val table: List<Standings>
)