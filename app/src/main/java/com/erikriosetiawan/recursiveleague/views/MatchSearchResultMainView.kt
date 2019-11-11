package com.erikriosetiawan.recursiveleague.views

import com.erikriosetiawan.recursiveleague.models.LastMatch

interface MatchSearchResultMainView {

    fun showLoading()
    fun hideLoading()
    fun showMatchSearchResultList(data: List<LastMatch>?)
}