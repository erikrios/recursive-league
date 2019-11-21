package com.erikriosetiawan.recursiveleague.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapters.LastMatchAdapter
import com.erikriosetiawan.recursiveleague.databases.favoriteMatchDatabase
import com.erikriosetiawan.recursiveleague.models.LastMatch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteLastMatchFragment : Fragment() {

    private lateinit var rvFavoriteLastMatch: RecyclerView
    private lateinit var root: View
    private var favoriteLastMatches: MutableList<LastMatch> = mutableListOf()
    private lateinit var lastMatchAdapter: LastMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_favorite_last_match, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(root)
        showFavorite()
        setRecyclerList(root)
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun initView(root: View) {
        rvFavoriteLastMatch = root.findViewById(R.id.rv_favorite_last_match)
        lastMatchAdapter = LastMatchAdapter(root.context, favoriteLastMatches)
    }

    private fun setRecyclerList(root: View) {
        rvFavoriteLastMatch.layoutManager = LinearLayoutManager(root.context)
        rvFavoriteLastMatch.adapter = lastMatchAdapter
    }

    private fun showFavorite() {
        favoriteLastMatches.clear()
        context?.favoriteMatchDatabase?.use {
            val result = select(LastMatch.TABLE_FAVORITE_LAST_MATCH)
            val favorites = result.parseList(classParser<LastMatch>())
            favoriteLastMatches.addAll(favorites)
            lastMatchAdapter.notifyDataSetChanged()
        }
    }
}