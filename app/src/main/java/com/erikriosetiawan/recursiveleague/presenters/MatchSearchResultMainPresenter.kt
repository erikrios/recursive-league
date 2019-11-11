package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.MatchSearchResultResponse
import com.erikriosetiawan.recursiveleague.views.MatchSearchResultMainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchSearchResultMainPresenter(

    private val view: MatchSearchResultMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getMatchSearchResult(query: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getMatchSearchResult(query)),
                MatchSearchResultResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchSearchResultList(data.event)
            }
        }
    }
}