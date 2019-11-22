package com.erikriosetiawan.recursiveleague.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.erikriosetiawan.recursiveleague.R

class TeamInfoFragment : Fragment() {

    companion object {
        const val DESCRIPTION_KEY = "description_key"
    }

    private var description: String? = null

    private lateinit var tvDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getData()
        bindViews()
    }

    private fun getData() {
        val bundle = this.arguments
        if (bundle != null)
            description = bundle.getString(DESCRIPTION_KEY)
    }

    private fun initViews() {
        activity?.let {
            tvDescription = it.findViewById(R.id.tv_team_description)
        }
    }

    private fun bindViews() {
        tvDescription.text = description
    }
}
