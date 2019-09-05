package com.erikriosetiawan.recursiveleague.presenter

import com.erikriosetiawan.recursiveleague.api.ApiRepository
import com.erikriosetiawan.recursiveleague.api.TheSportDBApi
import com.erikriosetiawan.recursiveleague.model.LeagueDetailsResponse
import com.erikriosetiawan.recursiveleague.view.LeagueDetailsMainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeagueDetailsMainPresenter(

    private val view: LeagueDetailsMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLegueDetailsList(idLeague: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLeagueDetails(idLeague)),
                LeagueDetailsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLeagueDetailsList(data.leagueDetails)
            }
        }
    }
}