package com.erikriosetiawan.recursiveleague.model

import com.google.gson.annotations.SerializedName

data class LastMatch(
    @SerializedName("idEvent")
    val idEvent: String?,

    @SerializedName("intHomeScore")
    val homeScore: String?,

    @SerializedName("intAwayScore")
    val awayScore: String?,

    @SerializedName("strThumb")
    val thumb: String?
)