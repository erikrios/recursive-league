package com.erikriosetiawan.recursiveleague.activity.ui.leaguedetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.activity.LeagueDetailsActivity
import com.erikriosetiawan.recursiveleague.api.ApiRepository
import com.erikriosetiawan.recursiveleague.model.League
import com.erikriosetiawan.recursiveleague.model.LeagueDetails
import com.erikriosetiawan.recursiveleague.presenter.LeagueDetailsMainPresenter
import com.erikriosetiawan.recursiveleague.view.LeagueDetailsMainView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class LeagueDetailsFragment : Fragment(), LeagueDetailsMainView, View.OnClickListener {

    private lateinit var leagueDetailsViewModel: LeagueDetailsViewModel
    private lateinit var idLeague: String
    private lateinit var presenter: LeagueDetailsMainPresenter
    private var leagueDetails: MutableList<LeagueDetails?>? = mutableListOf()

    private lateinit var imgLeagueBadge: ImageView
    private lateinit var tvLeagueName: TextView
    private lateinit var tvLeagueCountry: TextView
    private lateinit var tvLeagueFirstEvent: TextView
    private lateinit var tvLeagueDescription: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var imgLeagueWebsite: ImageView
    private lateinit var imgLeagueFacebook: ImageView
    private lateinit var imgLeagueTwitter: ImageView
    private lateinit var imgLeagueYoutube: ImageView
    private lateinit var imgRss: ImageView

    companion object {
        private var webSiteLink: String? = null
        private var facebookLink: String? = null
        private var twitterLink: String? = null
        private var youtubeLink: String? = null
        private var rssLink: String? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leagueDetailsViewModel =
            ViewModelProviders.of(this).get(LeagueDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_league_details, container, false)
        val textView: TextView = root.findViewById(R.id.tv_league_description_title)
        leagueDetailsViewModel.text.observe(this, Observer {
            textView.text = it
        })

        initView(root)
        getIntentIdLeague()

        val gson = Gson()
        val request = ApiRepository()
        presenter = LeagueDetailsMainPresenter(this, request, gson)
        presenter.getLegueDetailsList(idLeague)
        return root
    }

    private fun getIntentIdLeague() {
        val league: League = activity!!.intent.getParcelableExtra(LeagueDetailsActivity.LEAGUE_KEY)
        idLeague = league.idLeague!!
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showLeagueDetailsList(data: List<LeagueDetails>?) {
        leagueDetails?.clear()
        leagueDetails?.addAll(data!!)
        Picasso.get()
            .load(leagueDetails?.get(0)?.leagueBadge)
            .into(imgLeagueBadge)
        tvLeagueName.text = leagueDetails?.get(0)?.leagueName
        tvLeagueCountry.text = leagueDetails?.get(0)?.country
        tvLeagueFirstEvent.text = leagueDetails?.get(0)?.firstEvent
        tvLeagueDescription.text = leagueDetails?.get(0)?.leagueDescription

        webSiteLink = leagueDetails?.get(0)?.website
        facebookLink = leagueDetails?.get(0)?.facebook
        twitterLink = leagueDetails?.get(0)?.twitter
        youtubeLink = leagueDetails?.get(0)?.youtube
        rssLink = leagueDetails?.get(0)?.rss
    }

    private fun initView(root: View) {
        imgLeagueBadge = root.findViewById(R.id.img_league_badge)
        tvLeagueName = root.findViewById(R.id.tv_league_name)
        tvLeagueCountry = root.findViewById(R.id.tv_league_country)
        tvLeagueFirstEvent = root.findViewById(R.id.tv_league_first_event)
        tvLeagueDescription = root.findViewById(R.id.tv_league_description)
        progressBar = root.findViewById(R.id.progress_bar)
        imgLeagueWebsite = root.findViewById(R.id.img_league_website)
        imgLeagueWebsite.setOnClickListener(this)
        imgLeagueFacebook = root.findViewById(R.id.img_league_facebook)
        imgLeagueFacebook.setOnClickListener(this)
        imgLeagueTwitter = root.findViewById(R.id.img_league_twitter)
        imgLeagueTwitter.setOnClickListener(this)
        imgLeagueYoutube = root.findViewById(R.id.img_league_youtube)
        imgLeagueYoutube.setOnClickListener(this)
        imgRss = root.findViewById(R.id.img_league_rss)
        imgRss.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_league_website -> setUrlIntent(webSiteLink)
            R.id.img_league_facebook -> setUrlIntent(facebookLink)
            R.id.img_league_twitter -> setUrlIntent(twitterLink)
            R.id.img_league_youtube -> setUrlIntent(youtubeLink)
            R.id.img_league_rss -> setUrlIntent(rssLink)
        }
    }

    private fun setUrlIntent(url: String?) {
        if (url != null) {
            if (url.contains("http://")) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$url"))
                startActivity(intent)
            }
        }
    }
}