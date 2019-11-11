package com.erikriosetiawan.recursiveleague.activities.ui.nextmatch

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
import com.erikriosetiawan.recursiveleague.activities.LeagueDetailsActivity
import com.erikriosetiawan.recursiveleague.adapters.NextMatchAdapter
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.League
import com.erikriosetiawan.recursiveleague.models.NextMatch
import com.erikriosetiawan.recursiveleague.presenters.NextMatchMainPresenter
import com.erikriosetiawan.recursiveleague.views.NextMatchMainView
import com.google.gson.Gson

class NextMatchFragment : Fragment(), NextMatchMainView {

    private lateinit var nextMatchViewModel: NextMatchViewModel

    private var nextMatches: MutableList<NextMatch> = mutableListOf()
    private lateinit var idLeague: String
    private lateinit var presenter: NextMatchMainPresenter

    private lateinit var root: View

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nextMatchViewModel =
            ViewModelProviders.of(this).get(NextMatchViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_next_match, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(root)
        getIntentIdLeague()

        val gson = Gson()
        val request = ApiRepository()
        presenter = NextMatchMainPresenter(this, request, gson)
        presenter.getNextMatchList(idLeague)
    }

    private fun setRecyclerList(root: View) {
        val rvNextMatch = root.findViewById<RecyclerView>(R.id.rv_next_match)
        rvNextMatch.layoutManager = LinearLayoutManager(root.context)
        rvNextMatch.adapter = NextMatchAdapter(root.context, nextMatches)
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

    override fun showNextMatchList(data: List<NextMatch>?) {
        nextMatches.clear()
        nextMatches.addAll(data!!)
        setRecyclerList(root)
    }
}