package com.erikriosetiawan.recursiveleague.view

import com.erikriosetiawan.recursiveleague.model.MatchDetails

interface MatchDetailsMainView {

    fun showLoading()
    fun hideLoading()
    fun showMatchDetailsList(data: List<MatchDetails>?)
}