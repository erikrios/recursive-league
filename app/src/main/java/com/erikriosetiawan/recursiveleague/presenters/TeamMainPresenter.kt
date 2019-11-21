package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.TeamResponse
import com.erikriosetiawan.recursiveleague.views.TeamMainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamMainPresenter(

    private val view: TeamMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamList(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {

            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getTeam(idLeague)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }
}