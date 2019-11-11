package com.erikriosetiawan.recursiveleague.models

import com.google.gson.annotations.SerializedName

data class MatchSearchResultResponse(
    @SerializedName("event")
    val event: List<LastMatch>
)