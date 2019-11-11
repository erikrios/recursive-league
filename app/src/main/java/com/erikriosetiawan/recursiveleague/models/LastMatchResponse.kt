package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class LastMatchResponse(
    @SerializedName("events")
    val events: List<LastMatch>
)