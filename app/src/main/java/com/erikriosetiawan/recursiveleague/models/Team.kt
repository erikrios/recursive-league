package com.erikriosetiawan.recursiveleague.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: Long?,

    @SerializedName("idTeam")
    val idTeam: String? = null,

    @SerializedName("strTeam")
    val strTeam: String? = null,

    @SerializedName("strTeamBadge")
    val teamBadge: String? = null,

    @SerializedName("strDescriptionEN")
    val description: String? = null
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val DESCRIPTION: String = "DESCRIPTION"
    }
}