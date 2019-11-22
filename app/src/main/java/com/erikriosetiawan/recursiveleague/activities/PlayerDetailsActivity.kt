package com.erikriosetiawan.recursiveleague.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.models.Player
import com.squareup.picasso.Picasso

class PlayerDetailsActivity : AppCompatActivity() {

    private lateinit var imgThumb: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvTeam: TextView
    private lateinit var tvPosition: TextView
    private lateinit var tvDateBorn: TextView
    private lateinit var tvHeight: TextView
    private lateinit var tvDescription: TextView

    private lateinit var player: Player

    companion object {
        const val PLAYER_KEY = "player_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        initViews()
        getDataIntent()
        bindViews()
    }

    private fun initViews() {
        imgThumb = findViewById(R.id.img_player_thumb_details)
        tvName = findViewById(R.id.tv_player_name_details)
        tvTeam = findViewById(R.id.tv_player_team_details)
        tvPosition = findViewById(R.id.tv_player_position_details)
        tvDateBorn = findViewById(R.id.tv_player_date_born_details)
        tvHeight = findViewById(R.id.tv_player_height_details)
        tvDescription = findViewById(R.id.tv_player_description_details)
    }

    private fun getDataIntent() {
        player = intent.getParcelableExtra(PLAYER_KEY)
    }

    private fun bindViews() {
        Picasso.get().load(player.thumb).into(imgThumb)
        tvName.text = player.playerName
        tvTeam.text = player.teamName
        tvPosition.text = player.position
        tvDateBorn.text = player.dateBorn
        tvHeight.text = player.height
        tvDescription.text = player.description
    }
}
