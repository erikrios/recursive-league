package com.erikriosetiawan.recursiveleague.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.models.Standings

class StandingsAdapter(private val standinges: List<Standings>, private val context: Context) :
    RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_standings, parent, false))

    override fun getItemCount(): Int = standinges.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(standinges[position]) {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvName: TextView = view.findViewById(R.id.tv_name)
        private val tvPlayed: TextView = view.findViewById(R.id.tv_played)
        private val tvWin: TextView = view.findViewById(R.id.tv_win)
        private val tvDraw: TextView = view.findViewById(R.id.tv_draw)
        private val tvLoss: TextView = view.findViewById(R.id.tv_loss)
        private val tvTotalPoint: TextView = view.findViewById(R.id.tv_totalPoint)

        fun bindItem(standings: Standings, listener: (Standings) -> Unit) {
            tvName.text = standings.name
            tvPlayed.text = standings.played
            tvWin.text = standings.win
            tvDraw.text = standings.draw
            tvLoss.text = standings.loss
            tvTotalPoint.text = standings.totalPoint

            itemView.setOnClickListener { listener(standings) }
        }
    }
}