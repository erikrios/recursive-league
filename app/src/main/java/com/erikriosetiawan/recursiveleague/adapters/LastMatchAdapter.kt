package com.erikriosetiawan.recursiveleague.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.activities.MatchDetailsActivity
import com.erikriosetiawan.recursiveleague.models.LastMatch
import com.squareup.picasso.Picasso

class LastMatchAdapter(
    private val context: Context,
    private val lastMatches: List<LastMatch>
) :
    RecyclerView.Adapter<LastMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_last_match, parent, false))

    override fun getItemCount(): Int = lastMatches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(lastMatches[position]) {
            val dataIntent = Intent(context, MatchDetailsActivity::class.java)
            dataIntent.putExtra(MatchDetailsActivity.EVENT_ID_KEY, lastMatches[position].idEvent)
            context.startActivity(dataIntent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgThumb: ImageView = view.findViewById(R.id.img_thumb)
        private val tvEvent: TextView = view.findViewById(R.id.tv_event)
        private val tvHomeScore: TextView = view.findViewById(R.id.tv_home_score)
        private val tvAwayScore: TextView = view.findViewById(R.id.tv_away_score)

        fun bindItem(lastMatch: LastMatch, listener: (LastMatch) -> Unit) {
            lastMatch.thumb?.let { Picasso.get().load(it).into(imgThumb) }
            tvEvent.text = lastMatch.event
            if ((lastMatch.homeScore == null) && lastMatch.awayScore == null) {
                tvHomeScore.text = "-"
                tvAwayScore.text = "-"
            } else {
                tvHomeScore.text = lastMatch.homeScore
                tvAwayScore.text = lastMatch.awayScore
            }
            itemView.setOnClickListener { listener(lastMatch) }
        }
    }
}