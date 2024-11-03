package com.frogobox.coreapi.sport


import com.frogobox.coreutil.sport.response.Contracts
import com.frogobox.coreutil.sport.response.Countrys
import com.frogobox.coreutil.sport.response.Events
import com.frogobox.coreutil.sport.response.FormerTeams
import com.frogobox.coreutil.sport.response.Honors
import com.frogobox.coreutil.sport.response.Leagues
import com.frogobox.coreutil.sport.response.Players
import com.frogobox.coreutil.sport.response.Results
import com.frogobox.coreutil.sport.response.Seasons
import com.frogobox.coreutil.sport.response.Sports
import com.frogobox.coreutil.sport.response.Tables
import com.frogobox.coreutil.sport.response.Teams
import com.frogobox.coreutil.sport.response.Users
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TheSportDBApi
 * Copyright (C) 26/01/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.sport.source
 *
 */

interface SportApiService {

    // Search for team by name
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_FOR_TEAM)
    fun searchForTeamByName(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_TEAM_NAME) teamName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Teams>

    // Search for team short code
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_FOR_TEAM)
    fun searchForTeamByShortCode(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_SHORT_CODE_NAME) shortCode: String?
    ): Observable<com.frogobox.coreutil.sport.response.Teams>

    // Search for all players from team *Patreon ONLY*
    @GET(com.frogobox.coreutil.sport.SportUrl.PATREON_SEARCH_FOR_ALL_PLAYERS_FROM_TEAMS)
    fun searchForAllPlayer(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_TEAM_NAME) teamName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Players>

    // Search for players by player name
    @GET(com.frogobox.coreutil.sport.SportUrl.PATREON_SEARCH_FOR_ALL_PLAYERS_FROM_TEAMS)
    fun searchForPlayer(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_PLAYER_NAME) playerName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Players>

    // Search for players by player name and team name
    @GET(com.frogobox.coreutil.sport.SportUrl.PATREON_SEARCH_FOR_ALL_PLAYERS_FROM_TEAMS)
    fun searchForPlayer(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_PLAYER_NAME) playerName: String?,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_TEAM_NAME) teamName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Players>

    // Search for event by event name
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_FOR_EVENT)
    fun searchForEvent(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_EVENT_NAME) eventName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

    // Search For event by event name and season
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_FOR_EVENT)
    fun searchForEvent(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_EVENT_NAME) eventName: String?,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_SEASON) season: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

    // Search for event by event file name
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_FOR_EVENT_FILE_NAME)
    fun searchForEventFileName(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_EVENT_NAME) eventFileName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

    // List all sports
    @GET(com.frogobox.coreutil.sport.SportUrl.GET_ALL_SPORTS)
    fun getAllSports(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.sport.response.Sports>

    // List all leagues
    @GET(com.frogobox.coreutil.sport.SportUrl.GET_ALL_LEAGUES)
    fun getAllLeagues(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.sport.response.Leagues>

    // List all Leagues in a country
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_ALL_LEAGUES)
    fun searchAllLeagues(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_COUNTRY_NAME) countryName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Countrys>

    // List all Leagues in a country specific by sport
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_ALL_LEAGUES)
    fun searchAllLeagues(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_COUNTRY_NAME) countryName: String?,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_SPORT_NAME) sportName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Countrys>

    // List all Seasons in a League
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_ALL_SEASONS)
    fun searchAllSeasons(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idTeam: String?
    ): Observable<com.frogobox.coreutil.sport.response.Seasons>

    // List all Teams in a League
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_ALL_TEAMS)
    fun searchAllTeam(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_LEAGUE_NAME) league: String?
    ): Observable<com.frogobox.coreutil.sport.response.Teams>

    // List all Teams in Sport and Country
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_ALL_TEAMS)
    fun searchAllTeam(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_SPORT_NAME) sportName: String?,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_COUNTRY_NAME) countryName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Teams>

    // List All teams details in a league by Id
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_ALL_TEAMS)
    fun lookupAllTeam(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idLeague: String?
    ): Observable<com.frogobox.coreutil.sport.response.Teams>

    // List All players in a team by Team Id *Patreon ONLY*
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_ALL_PLAYER)
    fun lookupAllPlayer(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idTeam: String?
    ): Observable<com.frogobox.coreutil.sport.response.Players>

    // List all users loved teams and players
    @GET(com.frogobox.coreutil.sport.SportUrl.SEARCH_LOVES)
    fun searchLoves(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_USER_LOVED) userName: String?
    ): Observable<com.frogobox.coreutil.sport.response.Users>

    // League Details by Id
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_LEAGUE)
    fun lookupLeagues(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idLeague: String?
    ): Observable<com.frogobox.coreutil.sport.response.Leagues>

    // Team Details by Id
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_TEAM)
    fun lookupTeam(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idTeam: String?
    ): Observable<com.frogobox.coreutil.sport.response.Teams>

    // Player Details by Id
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_PLAYER)
    fun lookupPlayer(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idPlayer: String?
    ): Observable<com.frogobox.coreutil.sport.response.Players>

    // Event Details by Id
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_EVENT)
    fun lookupEvent(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idEvent: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

    // Player Honours by Player Id
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_HONOURS)
    fun lookupHonour(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idPlayer: String?
    ): Observable<com.frogobox.coreutil.sport.response.Honors>

    // Player Former Teams by Player Id
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_FORMER_TEAM)
    fun lookupFormerTeam(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idPlayer: String?
    ): Observable<com.frogobox.coreutil.sport.response.FormerTeams>

    // Player Contracts by Player Id
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_CONTRACTS)
    fun lookupContract(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idPlayer: String?
    ): Observable<com.frogobox.coreutil.sport.response.Contracts>

    // Lookup Table by League ID and Season
    @GET(com.frogobox.coreutil.sport.SportUrl.LOOKUP_TABLE)
    fun lookupTable(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_LEAGUE_NAME) idLeague: String?,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_SEASON) season: String?
    ): Observable<com.frogobox.coreutil.sport.response.Tables>

    // Next 5 Events by Team Id
    @GET(com.frogobox.coreutil.sport.SportUrl.EVENTS_NEXT)
    fun eventsNext(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idTeam: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

    // Next 15 Events by League Id
    @GET(com.frogobox.coreutil.sport.SportUrl.EVENTS_NEXT_LEAGUE)
    fun eventsNextLeague(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idLeague: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

    // Last 5 Events by Team Id
    @GET(com.frogobox.coreutil.sport.SportUrl.EVENTS_LAST)
    fun eventsLast(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idTeam: String?
    ): Observable<com.frogobox.coreutil.sport.response.Results>

    // Last 15 Events by League Id
    @GET(com.frogobox.coreutil.sport.SportUrl.EVENTS_PAST_LEAGUE)
    fun eventsPastLeague(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idLeague: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

    // Events in a specific round by league id/round/season
    @GET(com.frogobox.coreutil.sport.SportUrl.EVENTS_ROUND)
    fun eventsRound(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idLeague: String?,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ROUND) round: String?,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_SEASON) season: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

    // All events in specific league by season (Free tier limited to 200 events)
    @GET(com.frogobox.coreutil.sport.SportUrl.EVENTS_SEASONS)
    fun eventsSeason(
        @Path(com.frogobox.coreutil.sport.SportConstant.PATH_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_ID) idLeague: String?,
        @Query(com.frogobox.coreutil.sport.SportConstant.QUERY_SEASON) season: String?
    ): Observable<com.frogobox.coreutil.sport.response.Events>

}