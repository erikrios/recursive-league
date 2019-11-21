package com.erikriosetiawan.recursiveleague.lastmatch

import com.erikriosetiawan.recursiveleague.TestContextProvider
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.LastMatch
import com.erikriosetiawan.recursiveleague.models.LastMatchResponse
import com.erikriosetiawan.recursiveleague.presenters.LastMatchMainPresenter
import com.erikriosetiawan.recursiveleague.views.LastMatchMainView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LastMatchMainPresenterTest {

    @Mock
    private lateinit var view: LastMatchMainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: LastMatchMainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter =
            LastMatchMainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLastMatchList() {
        val lastMatches: MutableList<LastMatch> = mutableListOf()
        val response = LastMatchResponse(lastMatches)
        val id = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    LastMatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLastMatchList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLastMatchList(lastMatches)
            Mockito.verify(view).hideLoading()
        }
    }
}