package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.LastMatchResponse
import com.erikriosetiawan.recursiveleague.views.LastMatchMainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchMainPresenter(

    private val view: LastMatchMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLastMatchList(idLeague: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getLastMatch(idLeague)),
                LastMatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLastMatchList(data.events)
            }
        }
    }
}