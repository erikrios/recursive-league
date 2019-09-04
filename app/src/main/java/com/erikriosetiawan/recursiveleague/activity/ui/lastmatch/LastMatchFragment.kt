package com.erikriosetiawan.recursiveleague.activity.ui.lastmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapter.LastMatchAdapter
import com.erikriosetiawan.recursiveleague.model.LastMatch

class LastMatchFragment : Fragment() {

    private lateinit var lastMatchViewModel: LastMatchViewModel
    private var lastMatches: MutableList<LastMatch> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lastMatchViewModel =
            ViewModelProviders.of(this).get(LastMatchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_last_match, container, false)
        setRecyclerList(root)
        return root
    }

    private fun setRecyclerList(root: View) {
        initData(lastMatches)
        val rvLastMatch = root.findViewById<RecyclerView>(R.id.rv_last_match)
        rvLastMatch.layoutManager = LinearLayoutManager(root.context)
        rvLastMatch.adapter = LastMatchAdapter(root.context, lastMatches)
    }

    private fun initData(lastMatches: MutableList<LastMatch>) {
        val lastMatchOne = LastMatch(
            "602159",
            "3",
            "2",
            "https://www.thesportsdb.com/images/media/event/fanart/s885rf1567344469.jpg"
        )
        val lastMatchTwo = LastMatch(
            "602166",
            "2",
            "2",
            "https://www.thesportsdb.com/images/media/event/thumb/7n50lt1567262930.jpg"
        )

        lastMatches.add(lastMatchOne)
        lastMatches.add(lastMatchTwo)
    }
}