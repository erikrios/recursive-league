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
import com.erikriosetiawan.recursiveleague.models.Player
import com.squareup.picasso.Picasso

data class PlayerAdapter(private val context: Context, private val players: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_player, parent, false))

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(players[position]) {
            Toast.makeText(context, it.playerName, Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgPlayerThumb: ImageView = view.findViewById(R.id.img_player_thumb)
        private val tvPlayerName: TextView = view.findViewById(R.id.tv_player_name)
        private val tvPlayerPosition: TextView = view.findViewById(R.id.tv_player_position)
        private val tvPlayerDescription: TextView = view.findViewById(R.id.tv_player_description)

        fun bindItem(player: Player, listener: (Player) -> Unit) {
            Picasso.get().load(player.thumb).into(imgPlayerThumb)
            tvPlayerName.text = player.playerName
            tvPlayerPosition.text = player.position
            tvPlayerDescription.text = player.description

            itemView.setOnClickListener { listener(player) }
        }
    }
}