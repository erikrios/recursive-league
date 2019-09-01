package com.erikriosetiawan.recursiveleague.activity.ui.nextmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.erikriosetiawan.recursiveleague.R

class NextMatchFragment : Fragment() {

    private lateinit var nextMatchViewModel: NextMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nextMatchViewModel =
            ViewModelProviders.of(this).get(NextMatchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_next_match, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        nextMatchViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}