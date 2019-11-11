package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class LeagueDetails(
    @SerializedName("strLeague")
    val leagueName: String? = null,

    @SerializedName("strCountry")
    val country: String? = null,

    @SerializedName("dateFirstEvent")
    val firstEvent: String? = null,

    @SerializedName("strBadge")
    val leagueBadge: String? = null,

    @SerializedName("strDescriptionEN")
    val leagueDescription: String? = null,

    @SerializedName("strWebsite")
    val website: String? = null,

    @SerializedName("strFacebook")
    val facebook: String? = null,

    @SerializedName("strTwitter")
    val twitter: String? = null,

    @SerializedName("strYoutube")
    val youtube: String? = null,

    @SerializedName("strRSS")
    val rss: String? = null
)