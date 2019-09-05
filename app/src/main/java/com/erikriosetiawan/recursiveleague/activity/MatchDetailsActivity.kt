package com.erikriosetiawan.recursiveleague.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.api.ApiRepository
import com.erikriosetiawan.recursiveleague.model.MatchDetails
import com.erikriosetiawan.recursiveleague.presenter.MatchDetailsMainPresenter
import com.erikriosetiawan.recursiveleague.view.MatchDetailsMainView
import com.google.gson.Gson

class MatchDetailsActivity : AppCompatActivity(), MatchDetailsMainView {

    private var idEvent: String? = null
    private lateinit var presenter: MatchDetailsMainPresenter
    private var matchDetails: MutableList<MatchDetails> = mutableListOf()

    private lateinit var tvMatchDetailsSport: TextView
    private lateinit var tvMatchDetailsLeague: TextView
    private lateinit var tvMatchDetailsHomeTeam: TextView
    private lateinit var tvMatchDetailsAwayTeam: TextView
    private lateinit var tvMatchDetailsHomeScore: TextView
    private lateinit var tvMatchDetailsAwayScore: TextView
    private lateinit var tvMatchDetailsDateEvent: TextView
    private lateinit var progressBar: ProgressBar

    companion object {
        const val EVENT_ID_KEY = "ERKLOVEMEL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        initView()
        getIdEventIntent()

        val gson = Gson()
        val request = ApiRepository()
        presenter = MatchDetailsMainPresenter(this, request, gson)
        presenter.getMatchDetailsList(idEvent)
    }

    private fun getIdEventIntent() {
        idEvent = intent.getStringExtra(EVENT_ID_KEY)
    }

    private fun initView() {
        tvMatchDetailsSport = findViewById(R.id.tv_match_details_sport)
        tvMatchDetailsLeague = findViewById(R.id.tv_match_details_league)
        tvMatchDetailsHomeTeam = findViewById(R.id.tv_match_details_home_team)
        tvMatchDetailsAwayTeam = findViewById(R.id.tv_match_details_away_team)
        tvMatchDetailsHomeScore = findViewById(R.id.tv_match_details_home_score)
        tvMatchDetailsAwayScore = findViewById(R.id.tv_match_details_away_score)
        tvMatchDetailsDateEvent = findViewById(R.id.date_event)
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showMatchDetailsList(data: List<MatchDetails>?) {
        matchDetails.clear()
        matchDetails.addAll(data!!)

        tvMatchDetailsSport.text = matchDetails[0].sport
        tvMatchDetailsLeague.text = matchDetails[0].league
        tvMatchDetailsHomeTeam.text = matchDetails[0].homeTeam
        tvMatchDetailsAwayTeam.text = matchDetails[0].awayTeam

        if ((matchDetails[0].homeScore == null) && (matchDetails[0].awayScore == null)) {
            tvMatchDetailsHomeScore.text = "-"
            tvMatchDetailsAwayScore.text = "-"
        } else {
            tvMatchDetailsHomeScore.text = matchDetails[0].homeScore
            tvMatchDetailsAwayScore.text = matchDetails[0].awayScore
        }

        tvMatchDetailsDateEvent.text = matchDetails[0].dateEvent

        setActionBarTitle(matchDetails[0].event)
    }

    private fun setActionBarTitle(title: String?) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
            (supportActionBar as ActionBar).setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}