package com.erikriosetiawan.recursiveleague.activities.ui.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LeagueViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "League"
    }
    val text: LiveData<String> = _text
}
