package com.erikriosetiawan.recursiveleague.activities

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.databases.favoriteMatchDatabase
import com.erikriosetiawan.recursiveleague.fragments.TeamDetailsFragment
import com.erikriosetiawan.recursiveleague.models.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailsFavoriteActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var imgTeamDetailsBadge: ImageView
    private lateinit var tvTeamDetailsName: TextView
    private lateinit var ibFavorite: ImageButton
    private lateinit var tvTeamDetailsDescription: TextView
    private var team: Team? = null
    private lateinit var idTeam: String
    private var isFavorite: Boolean = false

    companion object {
        const val TEAM_KEY = "team_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details_favorite)

        initViews()
        getDataIntent()
        bindViews()

        favoriteStateTeam()
        setFavorite()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_favorite -> {
                if (isFavorite) removeFromFavoriteTeam() else addToFavoriteTeam()
                isFavorite = !isFavorite
                setFavorite()
            }
        }
    }

    private fun initViews() {
        imgTeamDetailsBadge = findViewById(R.id.img_team_details_badge)
        tvTeamDetailsName = findViewById(R.id.tv_team_details_name)
        ibFavorite = findViewById(R.id.ib_favorite)
        ibFavorite.setOnClickListener(this)
        tvTeamDetailsDescription = findViewById(R.id.tv_team_details_description)
    }

    private fun getDataIntent() {
        team = intent.getParcelableExtra(TEAM_KEY)
        idTeam = team?.idTeam ?: "null"
    }

    private fun bindViews() {
        Picasso.get().load(team?.teamBadge).into(imgTeamDetailsBadge)
        tvTeamDetailsName.text = team?.strTeam
        tvTeamDetailsDescription.text = team?.description
    }

    private fun addToFavoriteTeam() {
        try {
            favoriteMatchDatabase.use {
                insert(
                    Team.TABLE_FAVORITE_TEAM,
                    Team.ID_TEAM to team?.idTeam,
                    Team.TEAM_NAME to team?.strTeam,
                    Team.TEAM_BADGE to team?.teamBadge,
                    Team.DESCRIPTION to team?.description
                )
            }
            Toast.makeText(this, "${team?.strTeam} added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TeamDetailsFragment.TAG, e.localizedMessage)
        }
    }

    private fun removeFromFavoriteTeam() {
        try {
            favoriteMatchDatabase.use {
                delete(
                    Team.TABLE_FAVORITE_TEAM,
                    "(ID_TEAM = {idTeam})",
                    "idTeam" to idTeam
                )
            }
            Toast.makeText(this, "${team?.strTeam} removed from favorite", Toast.LENGTH_SHORT)
                .show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TeamDetailsFragment.TAG, e.localizedMessage)
        }
    }

    private fun favoriteStateTeam() {
        favoriteMatchDatabase.use {
            val result = select(Team.TABLE_FAVORITE_TEAM)
                .whereArgs(
                    "(ID_TEAM = {idTeam})",
                    "idTeam" to idTeam
                )
            val favorites = result.parseList(classParser<Team>())
            for (i in favorites.indices)
                if (favorites[i].idTeam == idTeam)
                    isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            ibFavorite.setImageResource(R.drawable.ic_added_to_favorites)
        else
            ibFavorite.setImageResource(R.drawable.ic_add_to_favorites)
    }
}
