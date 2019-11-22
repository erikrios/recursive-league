package com.erikriosetiawan.recursiveleague.apis

import com.erikriosetiawan.recursiveleague.BuildConfig

object TheSportDBApi {

    fun getLeagueDetails(idLeague: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupleague.php?id=$idLeague"


    fun getNextMatch(idLeague: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=$idLeague"

    fun getLastMatch(idLeague: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=$idLeague"

    fun getMatchDetails(idEvent: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=$idEvent"

    fun getMatchSearchResult(query: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e=$query"

    fun getStandings(idLeague: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookuptable.php?l=$idLeague"

    fun getTeam(idLeague: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_teams.php?id=$idLeague"

    fun getPlayer(idTeam: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id=$idTeam"

    fun geTeamSearchResult(query: String?): String =
        "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t=$query"
}