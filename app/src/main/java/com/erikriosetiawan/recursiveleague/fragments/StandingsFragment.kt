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
import com.erikriosetiawan.recursiveleague.adapters.StandingsAdapter
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.League
import com.erikriosetiawan.recursiveleague.models.Standings
import com.erikriosetiawan.recursiveleague.presenters.StandingsMainPresenter
import com.erikriosetiawan.recursiveleague.views.StandingsMainView
import com.google.gson.Gson

class StandingsFragment : Fragment(), StandingsMainView {

    private lateinit var root: View

    private var idLeague: String? = null
    private var standinges: MutableList<Standings> = mutableListOf()
    private lateinit var presenter: StandingsMainPresenter

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_standings, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(root)
        getIntentIdLeague()

        val gson = Gson()
        val request = ApiRepository()
        presenter = StandingsMainPresenter(this, request, gson)
        presenter.getStandingsList(idLeague)
    }

    private fun setRecyclerList(root: View) {
        val rvStandings = root.findViewById<RecyclerView>(R.id.rv_standings)
        rvStandings.layoutManager = LinearLayoutManager(root.context)
        rvStandings.adapter = StandingsAdapter(standinges, root.context)
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

    override fun showStandingsList(data: List<Standings>?) {
        standinges.clear()
        data?.let { standinges.addAll(it) }
        setRecyclerList(root)
    }
}
