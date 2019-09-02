package com.erikriosetiawan.recursiveleague.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
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
            Toast.makeText(context, nextMatches[position].event, Toast.LENGTH_SHORT).show()
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