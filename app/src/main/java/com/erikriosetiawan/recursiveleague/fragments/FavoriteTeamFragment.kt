package com.erikriosetiawan.recursiveleague.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapters.FavoriteTeamAdapter
import com.erikriosetiawan.recursiveleague.databases.favoriteMatchDatabase
import com.erikriosetiawan.recursiveleague.models.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamFragment : Fragment() {

    private lateinit var rvFavoriteTeam: RecyclerView
    private lateinit var root: View
    private var favoriteTeams: MutableList<Team> = mutableListOf()
    private lateinit var teamAdapter: FavoriteTeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        showFavorite()
        setRecyclerGrid()
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun initView() {
        rvFavoriteTeam = root.findViewById(R.id.rv_favorite_team)
        teamAdapter = FavoriteTeamAdapter(root.context, favoriteTeams)
    }

    private fun setRecyclerGrid() {
        rvFavoriteTeam.layoutManager = GridLayoutManager(root.context, 2)
        rvFavoriteTeam.adapter = teamAdapter
    }

    private fun showFavorite() {
        favoriteTeams.clear()
        context?.favoriteMatchDatabase?.use {
            val result = select(Team.TABLE_FAVORITE_TEAM)
            val favorites = result.parseList(classParser<Team>())
            favoriteTeams.addAll(favorites)
            teamAdapter.notifyDataSetChanged()
        }
    }
}
