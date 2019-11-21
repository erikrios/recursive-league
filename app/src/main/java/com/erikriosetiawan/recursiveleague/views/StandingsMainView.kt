package com.erikriosetiawan.recursiveleague.views

import com.erikriosetiawan.recursiveleague.models.Standings

interface StandingsMainView {

    fun showLoading()
    fun hideLoading()
    fun showStandingsList(data: List<Standings>?)
}