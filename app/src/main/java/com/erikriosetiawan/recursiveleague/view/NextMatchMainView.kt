package com.erikriosetiawan.recursiveleague.view

import com.erikriosetiawan.recursiveleague.model.NextMatch

interface NextMatchMainView {

    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<NextMatch>?)
}