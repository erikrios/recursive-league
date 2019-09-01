package com.erikriosetiawan.recursiveleague.activity.ui.leaguedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.erikriosetiawan.recursiveleague.R

class LeagueDetailsFragment : Fragment() {

    private lateinit var leagueDetailsViewModel: LeagueDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leagueDetailsViewModel =
            ViewModelProviders.of(this).get(LeagueDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_league_details, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        leagueDetailsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}