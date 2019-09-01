package com.erikriosetiawan.recursiveleague.activity.ui.lastmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.erikriosetiawan.recursiveleague.R

class LastMatchFragment : Fragment() {

    private lateinit var lastMatchViewModel: LastMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lastMatchViewModel =
            ViewModelProviders.of(this).get(LastMatchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_last_match, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        lastMatchViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}