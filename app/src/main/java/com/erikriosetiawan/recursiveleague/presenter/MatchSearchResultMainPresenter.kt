package com.erikriosetiawan.recursiveleague.presenter

import com.erikriosetiawan.recursiveleague.api.ApiRepository
import com.erikriosetiawan.recursiveleague.api.TheSportDBApi
import com.erikriosetiawan.recursiveleague.model.MatchSearchResultResponse
import com.erikriosetiawan.recursiveleague.view.MatchSearchResultMainView
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