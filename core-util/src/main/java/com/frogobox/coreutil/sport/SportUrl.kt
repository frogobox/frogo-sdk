package com.frogobox.coreutil.sport

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TheSportDBApi
 * Copyright (C) 06/04/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.sport.util
 *
 */
object SportUrl {

    // The Sport DB API base url
    const val BASE_URL = "https://www.thesportsdb.com/"

    // Default Free API KEY The Sport DB API
    const val API_KEY = "2"

    // Search for team by name & short code
    const val SEARCH_FOR_TEAM = "api/v1/json/{api_key}/searchteams.php"

    // Search for all players from team *Patreon ONLY*
    const val PATREON_SEARCH_FOR_ALL_PLAYERS_FROM_TEAMS = "api/v1/json/{api_key}/searchplayers.php"

    // Search for players by player name & team name
    const val SEARCH_FOR_PLAYER = "api/v1/json/{api_key}/searchplayers.php"

    // Search for event by event name
    const val SEARCH_FOR_EVENT = "api/v1/json/{api_key}/searchevents.php"

    // Search for event by event file name
    const val SEARCH_FOR_EVENT_FILE_NAME = "api/v1/json/{api_key}/searchfilename.php"

    // List all sports
    const val GET_ALL_SPORTS = "api/v1/json/{api_key}/all_sports.php"

    // List all leagues
    const val GET_ALL_LEAGUES = "api/v1/json/{api_key}/all_leagues.php"

    // List all Leagues in a country
    const val SEARCH_ALL_LEAGUES = "api/v1/json/{api_key}/search_all_leagues.php"

    // List all Seasons in a League
    const val SEARCH_ALL_SEASONS = "api/v1/json/{api_key}/search_all_seasons.php"

    // List all Teams in a League
    const val SEARCH_ALL_TEAMS = "api/v1/json/{api_key}/search_all_teams.php"

    // List All teams details in a league by Id
    const val LOOKUP_ALL_TEAMS = "api/v1/json/{api_key}/lookup_all_teams.php"

    // List All players in a team by Team Id *Patreon ONLY*
    const val LOOKUP_ALL_PLAYER = "api/v1/json/{api_key}/lookup_all_players.php"

    // List all users loved teams and players
    const val SEARCH_LOVES = "api/v1/json/{api_key}/searchloves.php"

    // League Details by Id
    const val LOOKUP_LEAGUE = "api/v1/json/{api_key}/lookupleague.php"

    // Team Details by Id
    const val LOOKUP_TEAM = "api/v1/json/{api_key}/lookupteam.php"

    // Player Details by Id
    const val LOOKUP_PLAYER = "api/v1/json/{api_key}/lookupplayer.php"

    // Event Details by Id
    const val LOOKUP_EVENT = "api/v1/json/{api_key}/lookupevent.php"

    // Player Honours by Player Id
    const val LOOKUP_HONOURS = "api/v1/json/{api_key}/lookuphonors.php"

    // Player Former Teams by Player Id
    const val LOOKUP_FORMER_TEAM = "api/v1/json/{api_key}/lookupformerteams.php"

    // Player Contracts by Player Id
    const val LOOKUP_CONTRACTS = "api/v1/json/{api_key}/lookupcontracts.php"

    // Event TV by Event Id *Patreon ONLY*
    const val LOOKUP_TV = "api/v1/json/{api_key}/lookuptv.php"

    // Lookup Table by League ID and Season
    const val LOOKUP_TABLE = "api/v1/json/{api_key}/lookuptable.php"

    // Next 5 Events by Team Id
    const val EVENTS_NEXT = "api/v1/json/{api_key}/eventsnext.php"

    // Next 15 Events by League Id
    const val EVENTS_NEXT_LEAGUE = "api/v1/json/{api_key}/eventsnextleague.php"

    // Last 5 Events by Team Id
    const val EVENTS_LAST = "api/v1/json/{api_key}/eventslast.php"

    // Last 15 Events by League Id
    const val EVENTS_PAST_LEAGUE = "api/v1/json/{api_key}/eventspastleague.php"

    // Events in a specific round by league id/round/season
    const val EVENTS_ROUND = "api/v1/json/{api_key}/eventsround.php"

    // Events on a specific day *Patreon ONLY*
    const val EVENTS_DAY = "api/v1/json/{api_key}/eventsday.php"

    // TV Events on a day (By Sport/Date/TV Station Country) channel (Latest) *Patreon ONLY*
    const val EVENTS_TV = "api/v1/json/{api_key}/eventstv.php"

    // All events in specific league by season (Free tier limited to 200 events)
    const val EVENTS_SEASONS = "api/v1/json/{api_key}/eventsseason.php"

}