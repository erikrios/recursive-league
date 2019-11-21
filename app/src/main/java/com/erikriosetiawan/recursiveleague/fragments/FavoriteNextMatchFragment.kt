package com.erikriosetiawan.recursiveleague.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapters.NextMatchAdapter
import com.erikriosetiawan.recursiveleague.databases.favoriteMatchDatabase
import com.erikriosetiawan.recursiveleague.models.NextMatch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteNextMatchFragment : Fragment() {

    private lateinit var rvFavoriteNextMatch: RecyclerView
    private lateinit var root: View
    private var favoriteNextMatches: MutableList<NextMatch> = mutableListOf()
    private lateinit var nextMatchAdapter: NextMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_favorite_next_match, container, false)
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
        rvFavoriteNextMatch = root.findViewById(R.id.rv_favorite_next_match)
        nextMatchAdapter = NextMatchAdapter(root.context, favoriteNextMatches)
    }

    private fun setRecyclerList(root: View) {
        rvFavoriteNextMatch.layoutManager = LinearLayoutManager(root.context)
        rvFavoriteNextMatch.adapter = nextMatchAdapter
    }

    private fun showFavorite() {
        favoriteNextMatches.clear()
        context?.favoriteMatchDatabase?.use {
            val result = select(NextMatch.TABLE_FAVORITE_NEXT_MATCH)
            val favorites = result.parseList(classParser<NextMatch>())
            favoriteNextMatches.addAll(favorites)
            nextMatchAdapter.notifyDataSetChanged()
        }
    }
}