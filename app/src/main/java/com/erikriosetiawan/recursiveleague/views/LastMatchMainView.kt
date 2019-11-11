package com.erikriosetiawan.recursiveleague.views

import com.erikriosetiawan.recursiveleague.models.LastMatch

interface LastMatchMainView {

    fun showLoading()
    fun hideLoading()
    fun showLastMatchList(data: List<LastMatch>?)
}