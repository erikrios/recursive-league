package com.erikriosetiawan.recursiveleague.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val idLeague: String?,
    val name: String?,
    val description: String?,
    val image: Int?
) : Parcelable