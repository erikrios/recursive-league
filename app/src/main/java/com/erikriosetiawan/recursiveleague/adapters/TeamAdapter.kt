package com.erikriosetiawan.recursiveleague.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.activities.LeagueDetailsActivity
import com.erikriosetiawan.recursiveleague.fragments.TeamDetailsFragment
import com.erikriosetiawan.recursiveleague.models.Team
import com.squareup.picasso.Picasso

class TeamAdapter(private val context: Context, private val teams: List<Team>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position]) {
            val bundle = Bundle()
            bundle.putParcelable(TeamDetailsFragment.KEY_TEAM, it)

            val teamDetailsFragment = TeamDetailsFragment()
            teamDetailsFragment.arguments = bundle
            val activity: LeagueDetailsActivity = context as LeagueDetailsActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.cl_container, teamDetailsFragment)
                .addToBackStack(null).commit()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgTeamBadge: ImageView = view.findViewById(R.id.img_team_badge)
        private val tvName: TextView = view.findViewById(R.id.tv_name)

        fun bindItem(team: Team, listener: (Team) -> Unit) {
            Picasso.get().load(team.teamBadge).into(imgTeamBadge)
            tvName.text = team.strTeam

            itemView.setOnClickListener { listener(team) }
        }
    }
}