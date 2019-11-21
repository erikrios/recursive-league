package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.LastMatchResponse
import com.erikriosetiawan.recursiveleague.views.LastMatchMainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LastMatchMainPresenter(

    private val view: LastMatchMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLastMatchList(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getLastMatch(idLeague)).await(),
                LastMatchResponse::class.java
            )

            view.hideLoading()
            view.showLastMatchList(data.events)
        }
    }
}