package com.erikriosetiawan.recursiveleague.views

import com.erikriosetiawan.recursiveleague.models.Team

interface TeamMainView {

    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>?)
}