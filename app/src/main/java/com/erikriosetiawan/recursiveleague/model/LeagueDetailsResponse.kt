package com.erikriosetiawan.recursiveleague.model

import com.google.gson.annotations.SerializedName

data class LeagueDetailsResponse(
    @SerializedName("leagues")
    val leagueDetails: List<LeagueDetails>
)