package com.erikriosetiawan.recursiveleague.matchdetails

import com.erikriosetiawan.recursiveleague.TestContextProvider
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.MatchDetails
import com.erikriosetiawan.recursiveleague.models.MatchDetailsResponse
import com.erikriosetiawan.recursiveleague.presenters.MatchDetailsMainPresenter
import com.erikriosetiawan.recursiveleague.views.MatchDetailsMainView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailsMainPresenterTest {

    @Mock
    private lateinit var view: MatchDetailsMainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchDetailsMainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailsMainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchDetailsList() {
        val matchesDetails: MutableList<MatchDetails> = mutableListOf()
        val response = MatchDetailsResponse(matchesDetails)
        val id = "602162"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchDetailsResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchDetailsList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchDetailsList(matchesDetails)
            Mockito.verify(view).hideLoading()
        }
    }
}