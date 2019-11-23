package com.erikriosetiawan.recursiveleague.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.astuetz.PagerSlidingTabStrip
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapters.MyPagerAdapter
import kotlinx.android.synthetic.main.favorites_fragment.*

class FavoritesFragment : Fragment() {

    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewPager = it.findViewById(R.id.pager)
        }
        val pagerAdapter = activity?.supportFragmentManager?.let { MyPagerAdapter(it) }
        pagerAdapter?.addFragment(FavoriteLastMatchFragment(), "Favorite Last Match")
        pagerAdapter?.addFragment(FavoriteNextMatchFragment(), "Favorite Next Match")
        pagerAdapter?.addFragment(FavoriteTeamFragment(), "Favorite Team Fragment")
        viewPager.adapter = pagerAdapter

        activity?.let {
            val tabs: PagerSlidingTabStrip = it.findViewById(R.id.tabs)
            tabs.shouldExpand = true
        }
        context?.let {
            tabs.indicatorColor = ContextCompat.getColor(it, R.color.colorPrimaryDark)
        }
        tabs.setViewPager(viewPager)
    }
}
