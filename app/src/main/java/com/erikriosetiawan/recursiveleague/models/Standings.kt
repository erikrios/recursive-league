package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class Standings(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("teamid")
    val teamId: String? = null,

    @SerializedName("played")
    val played: String? = null,

    @SerializedName("goalsfor")
    val goalsFor: String? = null,

    @SerializedName("goalsagainst")
    val goalsAgainst: String? = null,

    @SerializedName("goalsdifference")
    val goalsDifference: String? = null,

    @SerializedName("win")
    val win: String? = null,

    @SerializedName("draw")
    val draw: String? = null,

    @SerializedName("loss")
    val loss: String? = null,

    @SerializedName("total")
    val totalPoint: String? = null
)