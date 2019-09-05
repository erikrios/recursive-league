package com.erikriosetiawan.recursiveleague.presenter

import com.erikriosetiawan.recursiveleague.api.ApiRepository
import com.erikriosetiawan.recursiveleague.api.TheSportDBApi
import com.erikriosetiawan.recursiveleague.model.NextMatchResponse
import com.erikriosetiawan.recursiveleague.view.NextMatchMainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchMainPresenter(

    private val view: NextMatchMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getNextMatchList(idLeague: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getNextMatch(idLeague)),
                NextMatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showNextMatchList(data.events)
            }
        }
    }
}