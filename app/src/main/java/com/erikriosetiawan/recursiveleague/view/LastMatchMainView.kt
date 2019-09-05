package com.erikriosetiawan.recursiveleague.view

import com.erikriosetiawan.recursiveleague.model.LastMatch

interface LastMatchMainView {

    fun showLoading()
    fun hideLoading()
    fun showLastMatchList(data: List<LastMatch>?)
}