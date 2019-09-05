package com.erikriosetiawan.recursiveleague.view

import com.erikriosetiawan.recursiveleague.model.LastMatch

interface MatchSearchResultMainView {

    fun showLoading()
    fun hideLoading()
    fun showMatchSearchResultList(data: List<LastMatch>?)
}