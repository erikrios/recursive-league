package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class LastMatch(
    val id: Long?,

    @SerializedName("idEvent")
    val idEvent: String?,

    @SerializedName("intHomeScore")
    val homeScore: String?,

    @SerializedName("intAwayScore")
    val awayScore: String?,

    @SerializedName("strThumb")
    val thumb: String?,

    @SerializedName("strEvent")
    val event: String?
) {
    companion object {
        const val TABLE_FAVORITE_LAST_MATCH: String = "TABLE_FAVORITE_LAST_MATCH"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val THUMB: String = "THUMB"
        const val EVENT: String = "EVENT"
    }
}