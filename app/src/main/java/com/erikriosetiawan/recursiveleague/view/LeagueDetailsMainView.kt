package com.erikriosetiawan.recursiveleague.view

import com.erikriosetiawan.recursiveleague.model.LeagueDetails

interface LeagueDetailsMainView {

    fun showLoading()
    fun hideLoading()
    fun showLeagueDetailsList(data: List<LeagueDetails>?)
}