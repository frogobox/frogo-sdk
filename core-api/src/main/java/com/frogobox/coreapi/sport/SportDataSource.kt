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
import io.reactivex.rxjava3.core.Scheduler
import okhttp3.Interceptor

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
interface SportDataSource {

    // Switch For Using Chuck Interceptor
    fun usingChuckInterceptor(isDebug: Boolean, chuckerInterceptor: Interceptor): SportDataSource

    // Search for team by name
    fun searchForTeamByName(
        scheduler: Scheduler?,
        apiKey: String,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    )

    // Search for team short code
    fun searchForTeamByShortCode(
        scheduler: Scheduler?, apiKey: String,
        shortCode: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    )

    // Search for all players from team *Patreon ONLY*
    fun searchForAllPlayer(
        scheduler: Scheduler?,
        apiKey: String,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    )

    // Search for players by player name
    fun searchForPlayer(
        scheduler: Scheduler?,
        apiKey: String,
        playerName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    )

    // Search for players by player name and team name
    fun searchForPlayer(
        scheduler: Scheduler?, apiKey: String,
        playerName: String?,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    )

    // Search for event by event name
    fun searchForEvent(
        scheduler: Scheduler?,
        apiKey: String,
        eventName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // Search For event by event name and season
    fun searchForEvent(
        scheduler: Scheduler?, apiKey: String,
        eventName: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // Search for event by event file name
    fun searchForEventFileName(
        scheduler: Scheduler?, apiKey: String,
        eventFileName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // List all sports
    fun getAllSports(scheduler: Scheduler?, apiKey: String, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Sports>)

    // List all leagues
    fun getAllLeagues(scheduler: Scheduler?, apiKey: String, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>)

    // List all Leagues in a country
    fun searchAllLeagues(
        scheduler: Scheduler?,
        apiKey: String,
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    )

    // List all Leagues in a country specific by sport
    fun searchAllLeagues(
        scheduler: Scheduler?, apiKey: String,
        countryName: String?,
        sportName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    )

    // List all Seasons in a League
    fun searchAllSeasons(
        scheduler: Scheduler?,
        apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Seasons>
    )

    // List all Teams in a League
    fun searchAllTeam(
        scheduler: Scheduler?,
        apiKey: String,
        league: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    )

    // List all Teams in Sportname & Country Name
    fun searchAllTeam(
        scheduler: Scheduler?, apiKey: String,
        sportName: String?,
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    )

    // List All teams details in a league by Id
    fun lookupAllTeam(
        scheduler: Scheduler?,
        apiKey: String,
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    )

    // List All players in a team by Team Id *Patreon ONLY*
    fun lookupAllPlayer(
        scheduler: Scheduler?,
        apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    )

    // List all users loved teams and players
    fun searchLoves(
        scheduler: Scheduler?,
        apiKey: String,
        userName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Users>
    )

    // League Details by Id
    fun lookupLeagues(
        scheduler: Scheduler?,
        apiKey: String,
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>
    )

    // Team Details by Id
    fun lookupTeam(
        scheduler: Scheduler?,
        apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    )

    // Player Details by Id
    fun lookupPlayer(
        scheduler: Scheduler?,
        apiKey: String,
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    )

    // Event Details by Id
    fun lookupEvent(
        scheduler: Scheduler?,
        apiKey: String,
        idEvent: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // Player Honours by Player Id
    fun lookupHonour(
        scheduler: Scheduler?,
        apiKey: String,
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Honors>
    )

    // Player Former Teams by Player Id
    fun lookupFormerTeam(
        scheduler: Scheduler?,
        apiKey: String,
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.FormerTeams>
    )

    // Player Contracts by Player Id
    fun lookupContract(
        scheduler: Scheduler?,
        apiKey: String,
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Contracts>
    )

    // Lookup Table by League ID and Season
    fun lookupTable(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Tables>
    )

    // Next 5 Events by Team Id
    fun eventsNext(
        scheduler: Scheduler?,
        apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // Next 15 Events by League Id
    fun eventsNextLeague(
        scheduler: Scheduler?,
        apiKey: String,
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // Last 5 Events by Team Id
    fun eventsLast(
        scheduler: Scheduler?,
        apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Results>
    )

    // Last 15 Events by League Id
    fun eventsPastLeague(
        scheduler: Scheduler?,
        apiKey: String,
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // Events in a specific round by league id/round/season
    fun eventsRound(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        round: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // All events in specific league by season (Free tier limited to 200 events)
    fun eventsSeason(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    )

    // Event TV by Event Id *Patreon ONLY*

    // Events on a specific day *Patreon ONLY*

    // TV Events on a day (By Sport/Date/TV Station Country) channel (Latest) *Patreon ONLY*


}