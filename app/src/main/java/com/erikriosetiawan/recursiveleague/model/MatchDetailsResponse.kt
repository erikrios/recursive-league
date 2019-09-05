package com.erikriosetiawan.recursiveleague.model

import com.google.gson.annotations.SerializedName

data class MatchDetailsResponse(
    @SerializedName("events")
    val events: List<MatchDetails>
)