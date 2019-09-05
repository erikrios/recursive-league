package com.erikriosetiawan.recursiveleague.model

import com.google.gson.annotations.SerializedName

data class NextMatchResponse(
    @SerializedName("events")
    val events: List<NextMatch>
)