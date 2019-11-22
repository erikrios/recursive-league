package com.erikriosetiawan.recursiveleague.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.activities.LeagueDetailsActivity
import com.erikriosetiawan.recursiveleague.adapters.TeamAdapter
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.League
import com.erikriosetiawan.recursiveleague.models.Team
import com.erikriosetiawan.recursiveleague.presenters.TeamMainPresenter
import com.erikriosetiawan.recursiveleague.presenters.TeamSearchResultMainPresenter
import com.erikriosetiawan.recursiveleague.views.TeamMainView
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(), TeamMainView {

    private lateinit var root: View

    private var idLeague: String? = null
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var teamMainPresenter: TeamMainPresenter
    private lateinit var teamSearchResultMainPresenter: TeamSearchResultMainPresenter

    private lateinit var progressBar: ProgressBar

    companion object {
        const val QUERY_KEY = "query_key"
        const val VALIDATE_KEY = "validate_key"
    }

    private var validate: String? = null
    private var query: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_teams, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(root)
        getQuery()
        val gson = Gson()
        val request = ApiRepository()

        if (validate.equals(QUERY_KEY)) {
            teamSearchResultMainPresenter = TeamSearchResultMainPresenter(this, request, gson)
            teamSearchResultMainPresenter.getTeamSearchResultList(query)
        } else {
            getIntentIdLeague()
            teamMainPresenter = TeamMainPresenter(this, request, gson)
            teamMainPresenter.getTeamList(idLeague)
        }
    }

    private fun setRecyclerGrid(root: View) {
        val rvTeam = root.findViewById<RecyclerView>(R.id.rv_teams)
        rvTeam.layoutManager = GridLayoutManager(root.context, 2)
        rvTeam.adapter = TeamAdapter(root.context, teams)
    }

    private fun getIntentIdLeague() {
        val league: League? = activity?.intent?.getParcelableExtra(LeagueDetailsActivity.LEAGUE_KEY)
        idLeague = league?.idLeague
    }

    private fun getQuery() {
        val bundle = this.arguments
        if (bundle != null) {
            validate = bundle.getString(VALIDATE_KEY)
            query = bundle.getString(QUERY_KEY)
        }
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

    override fun showTeamList(data: List<Team>?) {
        teams.clear()
        data?.let { teams.addAll(it) }
        setRecyclerGrid(root)
    }
}