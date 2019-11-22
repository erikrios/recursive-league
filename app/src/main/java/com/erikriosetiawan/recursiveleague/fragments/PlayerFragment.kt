package com.erikriosetiawan.recursiveleague.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.recursiveleague.R
import com.erikriosetiawan.recursiveleague.adapters.PlayerAdapter
import com.erikriosetiawan.recursiveleague.apis.ApiRepository
import com.erikriosetiawan.recursiveleague.models.Player
import com.erikriosetiawan.recursiveleague.presenters.PlayerMainPresenter
import com.erikriosetiawan.recursiveleague.views.PlayerMainView
import com.google.gson.Gson

class PlayerFragment : Fragment(), PlayerMainView {

    companion object {
        const val TEAM_ID_KEY = "team_id_key"
    }

    private lateinit var rvPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var teamId: String? = null
    private var root: View? = null
    private val players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerMainPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_player, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getData()

        val gson = Gson()
        val request = ApiRepository()
        presenter = PlayerMainPresenter(this, request, gson)
        presenter.getPlayerList(teamId)
    }

    private fun getData() {
        val bundle = this.arguments
        if (bundle != null)
            teamId = bundle.getString(TEAM_ID_KEY)
    }

    private fun initViews() {
        root?.let {
            rvPlayer = it.findViewById(R.id.rv_player)
            progressBar = it.findViewById(R.id.progress_bar)
        }
    }

    private fun setRecyclerList() {
        rvPlayer.layoutManager = LinearLayoutManager(root?.context)
        rvPlayer.adapter = root?.context?.let { PlayerAdapter(it, players) }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showPlayerList(data: List<Player>?) {
        players.clear()
        data?.let { players.addAll(it) }
        setRecyclerList()
    }
}