package com.frogobox.api.sport

import android.content.Context
import com.frogobox.coreapi.sport.ISportApi
import com.frogobox.coreapi.sport.SportApi

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
import com.frogobox.sdk.ext.usingChuck
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import okhttp3.Interceptor

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TheSportDBApi
 * Copyright (C) 04/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.sport
 *
 */
class ConsumeTheSportDbApi(apiKey: String) : IConsumeTheSportDbApi {

    private var sportApi = SportApi(AndroidSchedulers.mainThread(), apiKey)

    override fun usingChuckInterceptor(
        isDebug: Boolean,
        chuckerInterceptor: Interceptor
    ): ISportApi {
        return sportApi.usingChuckInterceptor(isDebug, chuckerInterceptor)
    }

    override fun usingChuckInterceptor(isDebug: Boolean, context: Context): ISportApi {
        return usingChuckInterceptor(isDebug, context.usingChuck())
    }

    override fun searchForTeamByName(
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApi.searchForTeamByName(teamName, callback)
    }

    override fun searchForTeamByShortCode(
        shortCode: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApi.searchForTeamByShortCode(shortCode, callback)
    }

    override fun searchForAllPlayer(
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApi.searchForAllPlayer(teamName, callback)
    }

    override fun searchForPlayer(
        playerName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApi.searchForPlayer(playerName, callback)
    }

    override fun searchForPlayer(
        playerName: String?,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApi.searchForPlayer(playerName, teamName, callback)
    }

    override fun searchForEvent(
        eventName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApi.searchForEvent(eventName, callback)
    }

    override fun searchForEvent(
        eventName: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApi.searchForEvent(eventName, season, callback)
    }

    override fun searchForEventFileName(
        eventFileName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApi.searchForEventFileName(eventFileName, callback)

    }

    override fun getAllSports(callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Sports>) {
        sportApi.getAllSports(callback)
    }

    override fun getAllLeagues(callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>) {
        sportApi.getAllLeagues(callback)
    }

    override fun searchAllLeagues(
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    ) {
        sportApi.searchAllLeagues(countryName, callback)
    }

    override fun searchAllLeagues(
        countryName: String?,
        sportName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    ) {
        sportApi.searchAllLeagues(countryName, sportName, callback)
    }

    override fun searchAllSeasons(
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Seasons>
    ) {
        sportApi.searchAllSeasons(idTeam, callback)
    }

    override fun searchAllTeam(
        league: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApi.searchAllTeam(league, callback)
    }

    override fun searchAllTeam(
        sportName: String?,
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApi.searchAllTeam(sportName, countryName, callback)
    }

    override fun lookupAllTeam(idLeague: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>) {
        sportApi.lookupAllTeam(idLeague, callback)
    }

    override fun lookupAllPlayer(
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApi.lookupAllPlayer(idTeam, callback)
    }

    override fun searchLoves(userName: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Users>) {
        sportApi.searchLoves(userName, callback)
    }

    override fun lookupLeagues(
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>
    ) {
        sportApi.lookupLeagues(idLeague, callback)
    }

    override fun lookupTeam(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>) {
        sportApi.lookupTeam(idTeam, callback)
    }

    override fun lookupPlayer(idPlayer: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>) {
        sportApi.lookupPlayer(idPlayer, callback)
    }

    override fun lookupEvent(idEvent: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>) {
        sportApi.lookupEvent(idEvent, callback)
    }

    override fun lookupHonour(idPlayer: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Honors>) {
        sportApi.lookupHonour(idPlayer, callback)
    }

    override fun lookupFormerTeam(
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.FormerTeams>
    ) {
        sportApi.lookupFormerTeam(idPlayer, callback)
    }

    override fun lookupContract(
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Contracts>
    ) {
        sportApi.lookupContract(idPlayer, callback)
    }

    override fun lookupTable(
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Tables>
    ) {
        sportApi.lookupTable(idLeague, season, callback)
    }

    override fun eventsNext(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>) {
        sportApi.eventsNext(idTeam, callback)
    }

    override fun eventsNextLeague(
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApi.eventsNextLeague(idLeague, callback)
    }

    override fun eventsLast(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Results>) {
        sportApi.eventsLast(idTeam, callback)
    }

    override fun eventsPastLeague(
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApi.eventsPastLeague(idLeague, callback)
    }

    override fun eventsRound(
        idLeague: String?,
        round: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApi.eventsRound(idLeague, round, season, callback)
    }

    override fun eventsSeason(
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApi.eventsSeason(idLeague, season, callback)
    }

}