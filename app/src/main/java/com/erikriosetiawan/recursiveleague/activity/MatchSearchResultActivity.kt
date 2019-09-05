package com.erikriosetiawan.recursiveleague.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapter.LastMatchAdapter
import com.erikriosetiawan.recursiveleague.api.ApiRepository
import com.erikriosetiawan.recursiveleague.model.LastMatch
import com.erikriosetiawan.recursiveleague.presenter.MatchSearchResultMainPresenter
import com.erikriosetiawan.recursiveleague.view.MatchSearchResultMainView
import com.google.gson.Gson

class MatchSearchResultActivity : AppCompatActivity(), MatchSearchResultMainView {

    private var matchSearchResults: MutableList<LastMatch> = mutableListOf()
    private var query: String? = null
    private lateinit var presenter: MatchSearchResultMainPresenter

    private lateinit var progressBar: ProgressBar

    companion object {
        const val QUERY_KEY = "E15LOVE19M"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search_result)

        progressBar = findViewById(R.id.progress_bar)
        getQueryIntent()

        val gson = Gson()
        val request = ApiRepository()
        presenter = MatchSearchResultMainPresenter(this, request, gson)
        presenter.getMatchSearchResult(query)
    }

    private fun setRecyclerList() {
        val rvMatchSearchResult: RecyclerView = findViewById(R.id.rv_match_search)
        rvMatchSearchResult.layoutManager = LinearLayoutManager(this)
        rvMatchSearchResult.adapter = LastMatchAdapter(this, matchSearchResults)
    }

    private fun getQueryIntent() {
        query = intent.getStringExtra(QUERY_KEY)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showMatchSearchResultList(data: List<LastMatch>?) {
        if (data == null) {
            Toast.makeText(
                this@MatchSearchResultActivity,
                resources.getString(R.string.match_not_found),
                Toast.LENGTH_LONG
            ).show()
        } else {
            matchSearchResults.clear()
            matchSearchResults.addAll(data)
            setRecyclerList()
        }
    }
}
