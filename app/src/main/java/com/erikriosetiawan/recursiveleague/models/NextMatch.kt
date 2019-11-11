package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class NextMatch(
    val id: Long?,

    @SerializedName("idEvent")
    val idEvent: String?,

    @SerializedName("intHomeScore")
    val homeScore: String?,

    @SerializedName("intAwayScore")
    val awayScore: String?,

    @SerializedName("strEvent")
    val event: String?
) {
    companion object {
        const val TABLE_FAVORITE_NEXT_MATCH: String = "TABLE_FAVORITE_NEXT_MATCH"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val EVENT: String = "EVENT"
    }
}