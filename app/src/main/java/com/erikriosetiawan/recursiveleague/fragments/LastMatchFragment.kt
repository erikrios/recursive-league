package com.erikriosetiawan.recursiveleague.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.activities.LeagueDetailsActivity
import com.erikriosetiawan.recursiveleague.adapters.LastMatchAdapter
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.LastMatch
import com.erikriosetiawan.recursiveleague.models.League
import com.erikriosetiawan.recursiveleague.presenters.LastMatchMainPresenter
import com.erikriosetiawan.recursiveleague.views.LastMatchMainView
import com.google.gson.Gson

class LastMatchFragment : Fragment(), LastMatchMainView {


    private var lastMatches: MutableList<LastMatch> = mutableListOf()
    private var idLeague: String? = null
    private lateinit var presenter: LastMatchMainPresenter

    private lateinit var root: View

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_last_match, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(root)
        getIntentIdLeague()

        val gson = Gson()
        val request = ApiRepository()
        presenter = LastMatchMainPresenter(this, request, gson)
        presenter.getLastMatchList(idLeague)
    }

    private fun setRecyclerList(root: View) {
        val rvLastMatch = root.findViewById<RecyclerView>(R.id.rv_last_match)
        rvLastMatch.layoutManager = LinearLayoutManager(root.context)
        rvLastMatch.adapter = LastMatchAdapter(root.context, lastMatches)
    }

    private fun getIntentIdLeague() {
        val league: League? = activity?.intent?.getParcelableExtra(LeagueDetailsActivity.LEAGUE_KEY)
        idLeague = league?.idLeague
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
        data?.let { lastMatches.addAll(it) }
        setRecyclerList(root)
    }
}