package com.erikriosetiawan.recursiveleague.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(

    @SerializedName("idPlayer")
    val playerId: String? = null,

    @SerializedName("133604")
    val teamId: String? = null,

    @SerializedName("strNationality")
    val nationality: String? = null,

    @SerializedName("strPlayer")
    val playerName: String? = null,

    @SerializedName("strTeam")
    val teamName: String? = null,

    @SerializedName("strSport")
    val sport: String? = null,

    @SerializedName("dateBorn")
    val dateBorn: String? = null,

    @SerializedName("strNumber")
    val number: String? = null,

    @SerializedName("dateSigned")
    val dateSigned: String? = null,

    @SerializedName("strWage")
    val wage: String? = null,

    @SerializedName("strBirthLocation")
    val birthLocation: String? = null,

    @SerializedName("strDescriptionEN")
    val description: String? = null,

    @SerializedName("strPosition")
    val position: String? = null,

    @SerializedName("strHeight")
    val height: String? = null,

    @SerializedName("strThumb")
    val thumb: String? = null
) : Parcelable