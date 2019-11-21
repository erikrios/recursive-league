package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.StandingsResponse
import com.erikriosetiawan.recursiveleague.views.StandingsMainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingsMainPresenter(

    private val view: StandingsMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getStandingsList(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {

            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getStandings(idLeague)).await(),
                StandingsResponse::class.java
            )

            view.hideLoading()
            view.showStandingsList(data.table)
        }
    }
}