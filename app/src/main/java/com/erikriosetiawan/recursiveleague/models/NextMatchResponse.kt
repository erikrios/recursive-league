package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class NextMatchResponse(
    @SerializedName("events")
    val events: List<NextMatch>
)