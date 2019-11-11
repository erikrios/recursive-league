package com.erikriosetiawan.recursiveleague.activities.ui.leaguedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LeagueDetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "League Description"
    }
    val text: LiveData<String> = _text
}