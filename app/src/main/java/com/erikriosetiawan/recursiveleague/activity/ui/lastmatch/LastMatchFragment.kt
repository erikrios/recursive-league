package com.erikriosetiawan.recursiveleague.activity.ui.lastmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.activity.LeagueDetailsActivity
import com.erikriosetiawan.recursiveleague.adapter.LastMatchAdapter
import com.erikriosetiawan.recursiveleague.api.ApiRepository
import com.erikriosetiawan.recursiveleague.model.LastMatch
import com.erikriosetiawan.recursiveleague.model.League
import com.erikriosetiawan.recursiveleague.presenter.LastMatchMainPresenter
import com.erikriosetiawan.recursiveleague.view.LastMatchMainView
import com.google.gson.Gson

class LastMatchFragment : Fragment(), LastMatchMainView {

    private lateinit var lastMatchViewModel: LastMatchViewModel

    private var lastMatches: MutableList<LastMatch> = mutableListOf()
    private lateinit var idLeague: String
    private lateinit var presenter: LastMatchMainPresenter

    private lateinit var root: View

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lastMatchViewModel =
            ViewModelProviders.of(this).get(LastMatchViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_last_match, container, false)

        initView(root)
        getIntentIdLeague()

        val gson = Gson()
        val request = ApiRepository()
        presenter = LastMatchMainPresenter(this, request, gson)
        presenter.getLastMatchList(idLeague)
        return root
    }

    private fun setRecyclerList(root: View) {
        val rvLastMatch = root.findViewById<RecyclerView>(R.id.rv_last_match)
        rvLastMatch.layoutManager = LinearLayoutManager(root.context)
        rvLastMatch.adapter = LastMatchAdapter(root.context, lastMatches)
    }

    private fun getIntentIdLeague() {
        val league: League = activity!!.intent.getParcelableExtra(LeagueDetailsActivity.LEAGUE_KEY)
        idLeague = league.idLeague!!
    }

    private fun initView(root: View) {
        progressBar = root.findViewById(R.id.progress_bar)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showLastMatchList(data: List<LastMatch>?) {
        lastMatches.clear()
        lastMatches.addAll(data!!)
        setRecyclerList(root)
    }
}