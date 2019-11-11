package com.erikriosetiawan.recursiveleague.views

import com.erikriosetiawan.recursiveleague.models.MatchDetails

interface MatchDetailsMainView {

    fun showLoading()
    fun hideLoading()
    fun showMatchDetailsList(data: List<MatchDetails>?)
}