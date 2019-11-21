package com.erikriosetiawan.recursiveleague.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.fragments.FavoritesFragment
import com.erikriosetiawan.recursiveleague.fragments.LeagueFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            navView.selectedItemId = R.id.navigation_league
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchMatch: MenuItem? = menu?.findItem(R.id.item_search)
        val searchView: SearchView = searchMatch?.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @SuppressLint("DefaultLocale")
            override fun onQueryTextSubmit(query: String?): Boolean {
                val matchQuery = query?.format("%s", query)?.replace(" ", "_")
                val queryIntent = Intent(applicationContext, MatchSearchResultActivity::class.java)
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

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            val fragmentManager: FragmentManager
            val fragmentTransaction: FragmentTransaction
            val fragment: Fragment
            when (it.itemId) {
                R.id.navigation_league -> {
                    fragment = LeagueFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_home, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                else -> {
                    fragment = FavoritesFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_home, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
        }
}
