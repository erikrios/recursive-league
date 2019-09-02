package com.erikriosetiawan.recursiveleague.activity.ui.nextmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapter.NextMatchAdapter
import com.erikriosetiawan.recursiveleague.model.NextMatch

class NextMatchFragment : Fragment() {

    private lateinit var nextMatchViewModel: NextMatchViewModel
    private var nextMatches: MutableList<NextMatch> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nextMatchViewModel =
            ViewModelProviders.of(this).get(NextMatchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_next_match, container, false)
        setRecyclerList(root)
        return root
    }

    private fun setRecyclerList(root: View) {
        initData(nextMatches)
        val rvNextMatch = root.findViewById<RecyclerView>(R.id.rv_next_match)
        rvNextMatch.layoutManager = LinearLayoutManager(root.context)
        rvNextMatch.adapter = NextMatchAdapter(root.context, nextMatches)
    }

    private fun initData(nextMatches: MutableList<NextMatch>) {
        val nextMatchOne = NextMatch(602170, null, null, "Wolves vs Chelsea")
        val nextMatchTwo = NextMatch(602171, null, null, "Liverpool vs Newcastle")

        nextMatches.add(nextMatchOne)
        nextMatches.add(nextMatchTwo)
    }

}