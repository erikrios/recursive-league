package com.erikriosetiawan.recursiveleague.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
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

    private fun setActionBar(title: String?) {
        if (supportActionBar != null) (supportActionBar as ActionBar).title = title

    }
}