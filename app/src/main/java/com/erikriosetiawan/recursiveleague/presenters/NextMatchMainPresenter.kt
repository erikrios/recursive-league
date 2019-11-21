package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.NextMatchResponse
import com.erikriosetiawan.recursiveleague.views.NextMatchMainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextMatchMainPresenter(

    private val view: NextMatchMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getNextMatchList(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {

            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getNextMatch(idLeague)).await(),
                NextMatchResponse::class.java
            )

            view.hideLoading()
            view.showNextMatchList(data.events)
        }
    }
}