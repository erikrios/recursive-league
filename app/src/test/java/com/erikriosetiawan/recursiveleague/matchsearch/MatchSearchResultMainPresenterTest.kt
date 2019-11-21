package com.erikriosetiawan.recursiveleague.matchsearch

import com.erikriosetiawan.recursiveleague.TestContextProvider
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.LastMatch
import com.erikriosetiawan.recursiveleague.models.MatchSearchResultResponse
import com.erikriosetiawan.recursiveleague.presenters.MatchSearchResultMainPresenter
import com.erikriosetiawan.recursiveleague.views.MatchSearchResultMainView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchSearchResultMainPresenterTest {

    @Mock
    private lateinit var view: MatchSearchResultMainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchSearchResultMainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchSearchResultMainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchSearchResultList() {
        val searchResults: MutableList<LastMatch> = mutableListOf()
        val response = MatchSearchResultResponse(searchResults)
        val e = "Arsenal vs Tottenham"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchSearchResultResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchSearchResult(e)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchSearchResultList(searchResults)
            Mockito.verify(view).hideLoading()
        }
    }
}