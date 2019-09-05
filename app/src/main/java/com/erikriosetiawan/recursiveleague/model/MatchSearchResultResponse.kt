package com.erikriosetiawan.recursiveleague.model

import com.google.gson.annotations.SerializedName

data class MatchSearchResultResponse(
    @SerializedName("event")
    val event: List<LastMatch>
)