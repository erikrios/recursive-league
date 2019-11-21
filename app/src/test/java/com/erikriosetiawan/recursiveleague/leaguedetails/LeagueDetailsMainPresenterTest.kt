package com.erikriosetiawan.recursiveleague.leaguedetails

import com.erikriosetiawan.recursiveleague.TestContextProvider
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.LeagueDetails
import com.erikriosetiawan.recursiveleague.models.LeagueDetailsResponse
import com.erikriosetiawan.recursiveleague.presenters.LeagueDetailsMainPresenter
import com.erikriosetiawan.recursiveleague.views.LeagueDetailsMainView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueDetailsMainPresenterTest {

    @Mock
    private lateinit var view: LeagueDetailsMainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: LeagueDetailsMainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeagueDetailsMainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testgetLeagueDetailsList() {
        val leaguesDetails: MutableList<LeagueDetails> = mutableListOf()
        val response = LeagueDetailsResponse(leaguesDetails)
        val id = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueDetailsResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLegueDetailsList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueDetailsList(leaguesDetails)
            Mockito.verify(view).hideLoading()
        }
    }
}