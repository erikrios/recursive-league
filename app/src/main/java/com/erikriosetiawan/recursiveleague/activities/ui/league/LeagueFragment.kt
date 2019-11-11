package com.erikriosetiawan.recursiveleague.activities.ui.league

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.activities.LeagueDetailsActivity
import com.erikriosetiawan.recursiveleague.adapters.LeagueAdapter
import com.erikriosetiawan.recursiveleague.models.League
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class LeagueFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var viewModel: LeagueViewModel
    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LeagueViewModel::class.java)
        // TODO: Use the ViewModel
        initData()
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
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
}