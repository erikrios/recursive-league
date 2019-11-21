package com.erikriosetiawan.recursiveleague.nextmatch

import com.erikriosetiawan.recursiveleague.TestContextProvider
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.NextMatch
import com.erikriosetiawan.recursiveleague.models.NextMatchResponse
import com.erikriosetiawan.recursiveleague.presenters.NextMatchMainPresenter
import com.erikriosetiawan.recursiveleague.views.NextMatchMainView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchMainPresenterTest {

    @Mock
    private lateinit var view: NextMatchMainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: NextMatchMainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchMainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetNextMatchList() {
        val nextMatches: MutableList<NextMatch> = mutableListOf()
        val response = NextMatchResponse(nextMatches)
        val id = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    NextMatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatchList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showNextMatchList(nextMatches)
            Mockito.verify(view)
        }
    }
}