package com.erikriosetiawan.recursiveleague.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.activity.MatchDetailsActivity
import com.erikriosetiawan.recursiveleague.model.NextMatch

class NextMatchAdapter(
    private val context: Context,
    private val nextMatches: List<NextMatch>
) : RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_next_match, parent, false))

    override fun getItemCount(): Int = nextMatches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(nextMatches[position]) {
            val dataIntent = Intent(context, MatchDetailsActivity::class.java)
            dataIntent.putExtra(MatchDetailsActivity.EVENT_ID_KEY, nextMatches[position].idEvent)
            context.startActivity(dataIntent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvEvent: TextView = view.findViewById(R.id.tv_event)

        fun bindItem(nextMatch: NextMatch, listener: (NextMatch) -> Unit) {
            tvEvent.text = nextMatch.event
            itemView.setOnClickListener { listener(nextMatch) }
        }
    }

}