package com.erikriosetiawan.recursiveleague.model

import com.google.gson.annotations.SerializedName

data class LastMatchResponse(
    @SerializedName("events")
    val events: List<LastMatch>
)