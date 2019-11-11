package com.erikriosetiawan.recursiveleague.activities

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.databases.favoriteLastMatchDatabase
import com.erikriosetiawan.recursiveleague.databases.favoriteNextMatchDatabase
import com.erikriosetiawan.recursiveleague.models.LastMatch
import com.erikriosetiawan.recursiveleague.models.MatchDetails
import com.erikriosetiawan.recursiveleague.models.NextMatch
import com.erikriosetiawan.recursiveleague.presenters.MatchDetailsMainPresenter
import com.erikriosetiawan.recursiveleague.views.MatchDetailsMainView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

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

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    companion object {
        const val EVENT_ID_KEY = "EEKLOVEMRL"
        val TAG: String? = MatchDetailsActivity::class.java.simpleName
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

        favoriteStateLastMatch()
        favoriteStateNextMatch()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (matchDetails[0].homeScore != null && matchDetails[0].awayScore != null)
                    if (isFavorite) removeFromFavoriteLastMatch() else addToFavoriteLastMatch()
                else
                    if (isFavorite) removeFromFavoriteNextMatch() else addToFavoriteNextMatch()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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
        data?.let {
            matchDetails.addAll(it)
        }

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

    private fun addToFavoriteLastMatch() {
        try {
            favoriteLastMatchDatabase.use {
                insert(
                    LastMatch.TABLE_FAVORITE_LAST_MATCH,
                    LastMatch.ID_EVENT to matchDetails[0].eventId,
                    LastMatch.HOME_SCORE to matchDetails[0].homeScore,
                    LastMatch.AWAY_SCORE to matchDetails[0].awayScore,
                    LastMatch.THUMB to matchDetails[0].thumb,
                    LastMatch.EVENT to matchDetails[0].event
                )
            }
            Snackbar.make(
                findViewById(R.id.ll_match_details),
                "${matchDetails[0].event} added to favorite",
                Snackbar.LENGTH_SHORT
            ).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, e.localizedMessage)
        }
    }

    private fun removeFromFavoriteLastMatch() {
        try {
            favoriteLastMatchDatabase.use {
                delete(
                    LastMatch.TABLE_FAVORITE_LAST_MATCH,
                    "(ID_EVENT = {idEvent})",
                    "idEvent" to idEvent!!
                )
            }
            Snackbar.make(
                findViewById(R.id.ll_match_details),
                "${matchDetails[0].event} removed from favorite",
                Snackbar.LENGTH_SHORT
            ).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, e.localizedMessage)
        }
    }

    private fun addToFavoriteNextMatch() {
        try {
            favoriteNextMatchDatabase.use {
                insert(
                    NextMatch.TABLE_FAVORITE_NEXT_MATCH,
                    NextMatch.ID_EVENT to matchDetails[0].eventId,
                    NextMatch.HOME_SCORE to matchDetails[0].homeScore,
                    NextMatch.AWAY_SCORE to matchDetails[0].awayScore,
                    NextMatch.EVENT to matchDetails[0].event
                )
            }
            Snackbar.make(
                findViewById(R.id.ll_match_details),
                "${matchDetails[0].event} added to favorite",
                Snackbar.LENGTH_SHORT
            ).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, e.localizedMessage)
        }
    }

    private fun removeFromFavoriteNextMatch() {
        try {
            favoriteNextMatchDatabase.use {
                delete(
                    NextMatch.TABLE_FAVORITE_NEXT_MATCH,
                    "(ID_EVENT = {idEvent})",
                    "idEvent" to idEvent!!
                )
            }
            Snackbar.make(
                findViewById(R.id.ll_match_details),
                "${matchDetails[0].event} removed from favorite",
                Snackbar.LENGTH_SHORT
            ).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteStateLastMatch() {
        favoriteLastMatchDatabase.use {
            val result = select(LastMatch.TABLE_FAVORITE_LAST_MATCH)
                .whereArgs(
                    "(ID_EVENT = {idEvent})",
                    "idEvent" to idEvent!!
                )
            val favorites = result.parseList(classParser<LastMatch>())
            for (i in favorites.indices)
                if (favorites[i].idEvent == idEvent)
                    isFavorite = true
        }
    }

    private fun favoriteStateNextMatch() {
        favoriteNextMatchDatabase.use {
            val result = select(NextMatch.TABLE_FAVORITE_NEXT_MATCH)
                .whereArgs(
                    "(ID_EVENT = {idEvent})",
                    "idEvent" to idEvent!!
                )
            val favorites = result.parseList(classParser<NextMatch>())
            for (i in favorites.indices)
                if (favorites[i].idEvent == idEvent)
                    isFavorite = true
        }
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