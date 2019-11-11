package com.erikriosetiawan.recursiveleague.views

import com.erikriosetiawan.recursiveleague.models.LeagueDetails

interface LeagueDetailsMainView {

    fun showLoading()
    fun hideLoading()
    fun showLeagueDetailsList(data: List<LeagueDetails>?)
}