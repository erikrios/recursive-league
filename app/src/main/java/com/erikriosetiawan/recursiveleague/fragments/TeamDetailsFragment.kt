package com.erikriosetiawan.recursiveleague.fragments


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.astuetz.PagerSlidingTabStrip
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapters.MyPagerAdapter
import com.erikriosetiawan.recursiveleague.databases.favoriteMatchDatabase
import com.erikriosetiawan.recursiveleague.models.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailsFragment : Fragment(), View.OnClickListener {

    companion object {
        const val KEY_TEAM = "key_team"
        val TAG: String? = TeamDetailsFragment::class.java.simpleName
    }

    private lateinit var imgTeamBadge: ImageView
    private lateinit var tvTeamName: TextView
    private lateinit var viewPager: ViewPager
    private lateinit var ibFavorite: ImageButton
    private lateinit var idTeam: String

    private var team: Team? = null
    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getData()
        bindViews()
        val pagerAdapter = activity?.supportFragmentManager?.let { MyPagerAdapter(it) }

        val bundleDescription = Bundle()
        bundleDescription.putString(TeamInfoFragment.DESCRIPTION_KEY, team?.description)
        val teamInfoFragment = TeamInfoFragment()
        teamInfoFragment.arguments = bundleDescription

        val bundleTeamId = Bundle()
        bundleTeamId.putString(PlayerFragment.TEAM_ID_KEY, team?.idTeam)
        val playerFragment = PlayerFragment()
        playerFragment.arguments = bundleTeamId

        pagerAdapter?.addFragment(teamInfoFragment, "Team Info")
        pagerAdapter?.addFragment(playerFragment, "Player")
        viewPager.adapter = pagerAdapter

        activity?.let {
            val tabs: PagerSlidingTabStrip = it.findViewById(R.id.tabs)
            tabs.shouldExpand = true

            context?.let { its ->
                tabs.indicatorColor = ContextCompat.getColor(its, R.color.colorAccent)
            }
            tabs.setViewPager(viewPager)
        }

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
        activity?.let {
            viewPager = it.findViewById(R.id.pager)
            imgTeamBadge = it.findViewById(R.id.img_team_details_badge)
            tvTeamName = it.findViewById(R.id.tv_team_name)
            ibFavorite = it.findViewById(R.id.ib_favorite)
            ibFavorite.setOnClickListener(this)
        }
    }

    private fun getData() {
        val bundle = this.arguments
        if (bundle != null)
            team = bundle.getParcelable(KEY_TEAM)
        idTeam = team?.idTeam ?: "null"
    }

    private fun bindViews() {
        team?.let { Picasso.get().load(it.teamBadge).into(imgTeamBadge) }
        tvTeamName.text = team?.strTeam
    }

    private fun addToFavoriteTeam() {
        try {
            context?.favoriteMatchDatabase?.use {
                insert(
                    Team.TABLE_FAVORITE_TEAM,
                    Team.ID_TEAM to team?.idTeam,
                    Team.TEAM_NAME to team?.strTeam,
                    Team.TEAM_BADGE to team?.teamBadge,
                    Team.DESCRIPTION to team?.description
                )
            }
            Toast.makeText(context, "${team?.strTeam} added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, e.localizedMessage)
        }
    }

    private fun removeFromFavoriteTeam() {
        try {
            context?.favoriteMatchDatabase?.use {
                delete(
                    Team.TABLE_FAVORITE_TEAM,
                    "(ID_TEAM = {idTeam})",
                    "idTeam" to idTeam
                )
            }
            Toast.makeText(context, "${team?.strTeam} removed from favorite", Toast.LENGTH_SHORT)
                .show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, e.localizedMessage)
        }
    }

    private fun favoriteStateTeam() {
        context?.favoriteMatchDatabase?.use {
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