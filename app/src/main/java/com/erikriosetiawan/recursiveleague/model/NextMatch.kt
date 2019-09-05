package com.erikriosetiawan.recursiveleague.model

import com.google.gson.annotations.SerializedName

data class NextMatch(
    @SerializedName("idEvent")
    val idEvent: String?,

    @SerializedName("intHomeScore")
    val homeScore: String?,

    @SerializedName("intAwayScore")
    val awayScore: String?,

    @SerializedName("strEvent")
    val event: String?
)