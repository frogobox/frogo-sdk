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
import com.frogobox.coresdk.response.FrogoDataResponse
import okhttp3.Interceptor


/*
 * Created by faisalamir on 07/04/22
 * FrogoConsumeApi
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

interface ISportApi {

    // Switch For Using Chuck Interceptor
    fun usingChuckInterceptor(isDebug: Boolean, chuckerInterceptor: Interceptor): ISportApi

    // Search for team by name
    fun searchForTeamByName(teamName: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>)

    // Search for team short code
    fun searchForTeamByShortCode(shortCode: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>)

    // Search for all players from team *Patreon ONLY*
    fun searchForAllPlayer(teamName: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>)

    // Search for players by player name
    fun searchForPlayer(playerName: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>)

    // Search for players by player name and team name
    fun searchForPlayer(
        playerName: String?,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    )

    // Search for event by event name
    fun searchForEvent(eventName: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>)

    // Search For event by event name and season
    fun searchForEvent(
        eventName: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // Search for event by event file name
    fun searchForEventFileName(
        eventFileName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // List all sports
    fun getAllSports(callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Sports>)

    // List all leagues
    fun getAllLeagues(callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>)

    // List all Leagues in a country
    fun searchAllLeagues(countryName: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>)

    // List all Leagues in a country specific by sport
    fun searchAllLeagues(
        countryName: String?,
        sportName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    )

    // List all Seasons in a League
    fun searchAllSeasons(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Seasons>)

    // List all Teams in a League
    fun searchAllTeam(league: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>)

    // List all Teams in Sportname & Country Name
    fun searchAllTeam(
        sportName: String?,
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    )

    // List All teams details in a league by Id
    fun lookupAllTeam(idLeague: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>)

    // List All players in a team by Team Id *Patreon ONLY*
    fun lookupAllPlayer(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>)

    // List all users loved teams and players
    fun searchLoves(userName: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Users>)

    // League Details by Id
    fun lookupLeagues(idLeague: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>)

    // Team Details by Id
    fun lookupTeam(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>)

    // Player Details by Id
    fun lookupPlayer(idPlayer: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>)

    // Event Details by Id
    fun lookupEvent(idEvent: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>)

    // Player Honours by Player Id
    fun lookupHonour(idPlayer: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Honors>)

    // Player Former Teams by Player Id
    fun lookupFormerTeam(idPlayer: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.FormerTeams>)

    // Player Contracts by Player Id
    fun lookupContract(idPlayer: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Contracts>)

    // Lookup Table by League ID and Season
    fun lookupTable(
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Tables>
    )

    // Next 5 Events by Team Id
    fun eventsNext(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>)

    // Next 15 Events by League Id
    fun eventsNextLeague(idLeague: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>)

    // Last 5 Events by Team Id
    fun eventsLast(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Results>)

    // Last 15 Events by League Id
    fun eventsPastLeague(idLeague: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>)

    // Events in a specific round by league id/round/season
    fun eventsRound(
        idLeague: String?,
        round: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // All events in specific league by season (Free tier limited to 200 events)
    fun eventsSeason(
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

}