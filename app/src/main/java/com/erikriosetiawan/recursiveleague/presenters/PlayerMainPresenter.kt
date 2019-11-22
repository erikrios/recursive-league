package com.erikriosetiawan.recursiveleague.presenters

import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.apis.TheSportDBApi
import com.erikriosetiawan.recursiveleague.models.PlayerResponse
import com.erikriosetiawan.recursiveleague.views.PlayerMainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerMainPresenter(

    private val view: PlayerMainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPlayerList(idTeam: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {

            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getPlayer(idTeam)).await(),
                PlayerResponse::class.java
            )

            view.hideLoading()
            view.showPlayerList(data.player)
        }
    }
}