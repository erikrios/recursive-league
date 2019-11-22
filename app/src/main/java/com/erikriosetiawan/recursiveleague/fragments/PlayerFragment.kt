package com.erikriosetiawan.recursiveleague.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.erikriosetiawan.recursiveleague.R

class PlayerFragment : Fragment() {

    companion object {
        const val TEAM_ID_KEY = "team_id_key"
    }

    private var teamId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        val bundle = this.arguments
        if (bundle != null)
            teamId = bundle.getString(TEAM_ID_KEY)

    }
}
