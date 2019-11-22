package com.erikriosetiawan.recursiveleague.views

import com.erikriosetiawan.recursiveleague.models.Player

interface PlayerMainView {

    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>?)
}