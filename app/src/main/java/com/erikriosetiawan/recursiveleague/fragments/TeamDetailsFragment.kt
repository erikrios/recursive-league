package com.erikriosetiawan.recursiveleague.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.astuetz.PagerSlidingTabStrip
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapters.MyPagerAdapter
import com.erikriosetiawan.recursiveleague.models.Team
import com.squareup.picasso.Picasso

class TeamDetailsFragment : Fragment() {

    companion object {
        const val KEY_TEAM = "key_team"
    }

    private lateinit var imgTeamBadge: ImageView
    private lateinit var tvTeamName: TextView
    private lateinit var viewPager: ViewPager

    private var team: Team? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getData()
        bindViews()
        val pagerAdapter = activity?.supportFragmentManager?.let { MyPagerAdapter(it) }

        val bundleDescription = Bundle()
        bundleDescription.putString(TeamInfoFragment.DESCRIPTION_KEY, team?.description)
        val teamInfoFragment = TeamInfoFragment()
        teamInfoFragment.arguments = bundleDescription

        val bundleTeamId = Bundle()
        bundleTeamId.putString(PlayerFragment.TEAM_ID_KEY, team?.idTeam)
        val playerFragment = PlayerFragment()
        playerFragment.arguments = bundleTeamId

        pagerAdapter?.addFragment(teamInfoFragment, "Team Info")
        pagerAdapter?.addFragment(playerFragment, "Player")
        viewPager.adapter = pagerAdapter

        activity?.let {
            val tabs: PagerSlidingTabStrip = it.findViewById(R.id.tabs)
            tabs.shouldExpand = true

            context?.let { its ->
                tabs.indicatorColor = ContextCompat.getColor(its, R.color.colorAccent)
            }
            tabs.setViewPager(viewPager)
        }
    }

    private fun initViews() {
        activity?.let {
            viewPager = it.findViewById(R.id.pager)
            imgTeamBadge = it.findViewById(R.id.img_team_details_badge)
            tvTeamName = it.findViewById(R.id.tv_team_name)
        }
    }

    private fun getData() {
        val bundle = this.arguments
        if (bundle != null)
            team = bundle.getParcelable(KEY_TEAM)
    }

    private fun bindViews() {
        team?.let { Picasso.get().load(it.teamBadge).into(imgTeamBadge) }
        tvTeamName.text = team?.strTeam
    }
}
