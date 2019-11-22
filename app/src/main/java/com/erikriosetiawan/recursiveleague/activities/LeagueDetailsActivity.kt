package com.erikriosetiawan.recursiveleague.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class LeagueDetailsActivity : AppCompatActivity() {

    companion object {
        const val LEAGUE_KEY = "ERSMDT1519"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_details)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            navView.selectedItemId = R.id.navigation_league_details
        }
        setActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.team_search_menu, menu)

        val searchTeam: MenuItem? = menu?.findItem(R.id.item_team_search)
        val searchView: SearchView = searchTeam?.actionView as SearchView
        searchView.queryHint = getString(R.string.team_search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val bundle = Bundle()
                bundle.putString(TeamsFragment.VALIDATE_KEY, TeamsFragment.QUERY_KEY)
                bundle.putString(TeamsFragment.QUERY_KEY, query?.toLowerCase())
                val fragment = TeamsFragment()
                fragment.arguments = bundle
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frame_league_details, fragment)
                fragmentTransaction.commit()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setActionBar() {
        (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        (supportActionBar as ActionBar).setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            val fragmentManager: FragmentManager
            val fragmentTransaction: FragmentTransaction
            val fragment: Fragment
            when (it.itemId) {
                R.id.navigation_league_details -> {
                    fragment = LeagueDetailsFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_league_details, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_last_match -> {
                    fragment = LastMatchFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_league_details, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_next_match -> {
                    fragment = NextMatchFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_league_details, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_standings -> {
                    fragment = StandingsFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_league_details, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                else -> {
                    fragment = TeamsFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_league_details, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
        }
}