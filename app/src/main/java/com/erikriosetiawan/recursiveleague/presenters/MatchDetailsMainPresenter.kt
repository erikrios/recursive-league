package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.MatchDetailsResponse
import com.erikriosetiawan.recursiveleague.views.MatchDetailsMainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailsMainPresenter(

    private val view: MatchDetailsMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getMatchDetailsList(idEvent: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getMatchDetails(idEvent)),
                MatchDetailsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchDetailsList(data.events)
            }
        }
    }
}