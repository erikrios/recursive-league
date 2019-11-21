package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.MatchSearchResultResponse
import com.erikriosetiawan.recursiveleague.views.MatchSearchResultMainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchSearchResultMainPresenter(

    private val view: MatchSearchResultMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchSearchResult(query: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getMatchSearchResult(query)).await(),
                MatchSearchResultResponse::class.java
            )

            view.hideLoading()
            view.showMatchSearchResultList(data.event)
        }
    }
}