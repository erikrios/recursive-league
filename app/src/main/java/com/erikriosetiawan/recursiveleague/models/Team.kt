package com.erikriosetiawan.recursiveleague.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(

    @SerializedName("idTeam")
    val idTeam: String? = null,

    @SerializedName("strTeam")
    val strTeam: String? = null,

    @SerializedName("intFormedYear")
    val formedYear: String? = null,

    @SerializedName("strSport")
    val sport: String? = null,

    @SerializedName("strLeague")
    val league: String? = null,

    @SerializedName("idLeague")
    val idLeague: String? = null,

    @SerializedName("strStadium")
    val stadium: String? = null,

    @SerializedName("strStadiumDescription")
    val stadiumDescription: String? = null,

    @SerializedName("strStadiumLocation")
    val stadiumLocation: String? = null,

    @SerializedName("intStadiumCapacity")
    val stadiumCapacity: String? = null,

    @SerializedName("strDescriptionEN")
    val description: String? = null,

    @SerializedName("strCountry")
    val country: String? = null,

    @SerializedName("strTeamBadge")
    val teamBadge: String? = null
) : Parcelable