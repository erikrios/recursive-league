package com.erikriosetiawan.recursiveleague.views

import com.erikriosetiawan.recursiveleague.models.NextMatch

interface NextMatchMainView {

    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<NextMatch>?)
}