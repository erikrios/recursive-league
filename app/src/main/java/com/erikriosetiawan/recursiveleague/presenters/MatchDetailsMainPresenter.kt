package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.MatchDetailsResponse
import com.erikriosetiawan.recursiveleague.views.MatchDetailsMainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailsMainPresenter(

    private val view: MatchDetailsMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchDetailsList(idEvent: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getMatchDetails(idEvent)).await(),
                MatchDetailsResponse::class.java
            )

            view.hideLoading()
            view.showMatchDetailsList(data.events)
        }
    }
}