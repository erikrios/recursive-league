package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.LeagueDetailsResponse
import com.erikriosetiawan.recursiveleague.views.LeagueDetailsMainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeagueDetailsMainPresenter(

    private val view: LeagueDetailsMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLegueDetailsList(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getLeagueDetails(idLeague)).await(),
                LeagueDetailsResponse::class.java
            )

            view.hideLoading()
            view.showLeagueDetailsList(data.leagueDetails)
        }
    }
}