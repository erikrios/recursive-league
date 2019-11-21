package com.erikriosetiawan.recursiveleague.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.models.Team
import com.squareup.picasso.Picasso

class TeamAdapter(private val context: Context, private val teams: List<Team>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position]) {
            Toast.makeText(context, it.strTeam, Toast.LENGTH_SHORT).show()
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