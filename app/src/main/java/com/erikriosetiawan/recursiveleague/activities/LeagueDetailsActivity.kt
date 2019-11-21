package com.erikriosetiawan.recursiveleague.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.fragments.LastMatchFragment
import com.erikriosetiawan.recursiveleague.fragments.LeagueDetailsFragment
import com.erikriosetiawan.recursiveleague.fragments.NextMatchFragment
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
                else -> {
                    fragment = NextMatchFragment()
                    fragmentManager = supportFragmentManager
                    fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_league_details, fragment)
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
        }
}