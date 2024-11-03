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

class SportApi(
    private val scheduler: Scheduler?,
    private val apiKey: String
) : ISportApi {

    private val sportRepository = SportRepository

    override fun usingChuckInterceptor(
        isDebug: Boolean,
        chuckerInterceptor: Interceptor
    ): ISportApi {
        sportRepository.usingChuckInterceptor(isDebug, chuckerInterceptor)
        return this
    }

    override fun searchForTeamByName(
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportRepository.searchForTeamByName(
            scheduler, apiKey,
            teamName,
            callback
        )
    }

    override fun searchForTeamByShortCode(
        shortCode: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportRepository.searchForTeamByShortCode(
            scheduler, apiKey,
            shortCode,
            callback
        )
    }

    override fun searchForAllPlayer(
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportRepository.searchForAllPlayer(
            scheduler, apiKey,
            teamName,
            callback
        )
    }

    override fun searchForPlayer(
        playerName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportRepository.searchForPlayer(
            scheduler, apiKey,
            playerName,
            callback
        )
    }

    override fun searchForPlayer(
        playerName: String?,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportRepository.searchForPlayer(
            scheduler, apiKey,
            playerName,
            teamName,
            callback
        )
    }

    override fun searchForEvent(
        eventName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportRepository.searchForEvent(
            scheduler, apiKey,
            eventName,
            callback
        )
    }

    override fun searchForEvent(
        eventName: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportRepository.searchForEvent(
            scheduler, apiKey,
            eventName,
            season,
            callback
        )
    }

    override fun searchForEventFileName(
        eventFileName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {

        sportRepository.searchForEventFileName(
            scheduler, apiKey,
            eventFileName,
            callback
        )

    }

    override fun getAllSports(callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Sports>) {
        sportRepository.getAllSports(scheduler, apiKey, callback)
    }

    override fun getAllLeagues(callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>) {
        sportRepository.getAllLeagues(scheduler, apiKey, callback)
    }

    override fun searchAllLeagues(
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    ) {
        sportRepository.searchAllLeagues(
            scheduler, apiKey,
            countryName,
            callback
        )
    }

    override fun searchAllLeagues(
        countryName: String?,
        sportName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    ) {
        sportRepository.searchAllLeagues(
            scheduler, apiKey,
            countryName,
            sportName,
            callback
        )
    }

    override fun searchAllSeasons(
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Seasons>
    ) {
        sportRepository.searchAllSeasons(
            scheduler, apiKey,
            idTeam,
            callback
        )
    }

    override fun searchAllTeam(
        league: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {

        sportRepository.searchAllTeam(
            scheduler, apiKey,
            league,
            callback
        )
    }

    override fun searchAllTeam(
        sportName: String?,
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportRepository.searchAllTeam(
            scheduler, apiKey,
            sportName,
            countryName,
            callback
        )
    }

    override fun lookupAllTeam(idLeague: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>) {

        sportRepository.lookupAllTeam(
            scheduler, apiKey,
            idLeague,
            callback
        )

    }

    override fun lookupAllPlayer(
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportRepository.lookupAllPlayer(
            scheduler, apiKey,
            idTeam,
            callback
        )
    }

    override fun searchLoves(userName: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Users>) {
        sportRepository.searchLoves(
            scheduler, apiKey,
            userName,
            callback
        )
    }

    override fun lookupLeagues(
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>
    ) {
        sportRepository.lookupLeagues(
            scheduler, apiKey,
            idLeague,
            callback
        )
    }

    override fun lookupTeam(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>) {
        sportRepository.lookupTeam(
            scheduler, apiKey,
            idTeam,
            callback
        )
    }

    override fun lookupPlayer(idPlayer: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>) {
        sportRepository.lookupPlayer(
            scheduler, apiKey,
            idPlayer,
            callback
        )
    }

    override fun lookupEvent(idEvent: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>) {
        sportRepository.lookupEvent(
            scheduler, apiKey,
            idEvent,
            callback
        )
    }

    override fun lookupHonour(idPlayer: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Honors>) {
        sportRepository.lookupHonour(
            scheduler, apiKey,
            idPlayer,
            callback
        )
    }

    override fun lookupFormerTeam(
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.FormerTeams>
    ) {
        sportRepository.lookupFormerTeam(
            scheduler, apiKey,
            idPlayer,
            callback
        )
    }

    override fun lookupContract(
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Contracts>
    ) {
        sportRepository.lookupContract(
            scheduler, apiKey,
            idPlayer,
            callback
        )
    }

    override fun lookupTable(
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Tables>
    ) {
        sportRepository.lookupTable(
            scheduler, apiKey,
            idLeague,
            season,
            callback
        )
    }

    override fun eventsNext(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>) {
        sportRepository.eventsNext(
            scheduler, apiKey,
            idTeam,
            callback
        )
    }

    override fun eventsNextLeague(
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportRepository.eventsNextLeague(
            scheduler, apiKey,
            idLeague,
            callback
        )
    }

    override fun eventsLast(idTeam: String?, callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Results>) {
        sportRepository.eventsLast(
            scheduler, apiKey,
            idTeam,
            callback
        )
    }

    override fun eventsPastLeague(
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportRepository.eventsPastLeague(
            scheduler, apiKey,
            idLeague,
            callback
        )
    }

    override fun eventsRound(
        idLeague: String?,
        round: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportRepository.eventsRound(
            scheduler, apiKey,
            idLeague,
            round,
            season,
            callback
        )
    }

    override fun eventsSeason(
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportRepository.eventsSeason(
            scheduler, apiKey,
            idLeague,
            season,
            callback
        )
    }
}