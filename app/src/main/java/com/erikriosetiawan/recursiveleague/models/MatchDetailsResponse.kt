package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class MatchDetailsResponse(
    @SerializedName("events")
    val events: List<MatchDetails>
)