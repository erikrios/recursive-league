package com.erikriosetiawan.recursiveleague.model

import com.google.gson.annotations.SerializedName

data class MatchDetails(
    @SerializedName("idEvent")
    val eventId: String? = null,

    @SerializedName("strEvent")
    val event: String? = null,

    @SerializedName("strSport")
    val sport: String? = null,

    @SerializedName("strLeague")
    val league: String? = null,

    @SerializedName("strHomeTeam")
    val homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    val awayTeam: String? = null,

    @SerializedName("intHomeScore")
    val homeScore: String? = null,

    @SerializedName("intAwayScore")
    val awayScore: String? = null,

    @SerializedName("dateEvent")
    val dateEvent: String? = null,

    @SerializedName("strThumb")
    val thumb: String? = null
)