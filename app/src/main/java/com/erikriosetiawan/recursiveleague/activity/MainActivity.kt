package com.erikriosetiawan.recursiveleague.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapter.LeagueAdapter
import com.erikriosetiawan.recursiveleague.model.League
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class MainActivity : AppCompatActivity() {

    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()

        verticalLayout {
            recyclerView {
                lparams(width = matchParent, height = wrapContent)
                layoutManager = LinearLayoutManager(context)
                adapter = LeagueAdapter(leagues) {
                    startActivity<LeagueDetailsActivity>(
                        LeagueDetailsActivity.LEAGUE_KEY to it
                    )
                }
            }
        }

        setActionBar(resources.getString(R.string.league_list))
    }

    private fun initData() {
        val idLeague = resources.getStringArray(R.array.id_league)
        val name = resources.getStringArray(R.array.league_name)
        val description = resources.getStringArray(R.array.league_description)
        val image = resources.obtainTypedArray(R.array.league_image)
        leagues.clear()
        for (i in name.indices) {
            leagues.add(
                League(
                    idLeague[i],
                    name[i],
                    description[i],
                    image.getResourceId(i, 0)
                )
            )
        }
        image.recycle()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchMatch: MenuItem? = menu?.findItem(R.id.item_search)
        val searchView: SearchView = searchMatch?.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val matchQuery = query?.format("%s", query)?.replace(" ", "_")
                val queryIntent = Intent(this@MainActivity, MatchSearchResultActivity::class.java)
                queryIntent.putExtra(MatchSearchResultActivity.QUERY_KEY, matchQuery?.toLowerCase())
                startActivity(queryIntent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setActionBar(title: String?) {
        if (supportActionBar != null) (supportActionBar as ActionBar).title = title
    }
}