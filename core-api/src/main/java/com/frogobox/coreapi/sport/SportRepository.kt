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
import com.frogobox.coresdk.ext.doApiRequest
import com.frogobox.coresdk.response.FrogoDataResponse
import com.frogobox.coresdk.source.FrogoApiClient
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
object SportRepository : SportDataSource {

    private val TAG = SportRepository::class.java.simpleName
    private var sportApiService = FrogoApiClient.create<SportApiService>(com.frogobox.coreutil.sport.SportUrl.BASE_URL)

    // Switch For Using Chuck Interceptor
    override fun usingChuckInterceptor(
        isDebug: Boolean,
        chuckerInterceptor: Interceptor
    ): SportDataSource {
        sportApiService = FrogoApiClient.create(com.frogobox.coreutil.sport.SportUrl.BASE_URL, isDebug, chuckerInterceptor)
        return this
    }

    override fun searchForTeamByName(
        scheduler: Scheduler?, apiKey: String,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApiService.searchForTeamByName(apiKey, teamName).doApiRequest(scheduler, callback) {}

    }

    override fun searchForTeamByShortCode(
        scheduler: Scheduler?, apiKey: String,
        shortCode: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApiService.searchForTeamByShortCode(apiKey, shortCode)
            .doApiRequest(scheduler, callback) {}
    }

    override fun searchForAllPlayer(
        scheduler: Scheduler?, apiKey: String,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApiService.searchForAllPlayer(apiKey, teamName).doApiRequest(scheduler, callback) {}
    }

    override fun searchForPlayer(
        scheduler: Scheduler?, apiKey: String,
        playerName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApiService.searchForPlayer(apiKey, playerName).doApiRequest(scheduler, callback) {}
    }

    override fun searchForPlayer(
        scheduler: Scheduler?, apiKey: String,
        playerName: String?,
        teamName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApiService.searchForPlayer(apiKey, playerName, teamName)
            .doApiRequest(scheduler, callback) {}
    }

    override fun searchForEvent(
        scheduler: Scheduler?, apiKey: String,
        eventName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.searchForEvent(apiKey, eventName).doApiRequest(scheduler, callback) {}
    }

    override fun searchForEvent(
        scheduler: Scheduler?, apiKey: String,
        eventName: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.searchForEvent(apiKey, eventName, season)
            .doApiRequest(scheduler, callback) {}
    }

    override fun searchForEventFileName(
        scheduler: Scheduler?, apiKey: String,
        eventFileName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.searchForEventFileName(apiKey, eventFileName)
            .doApiRequest(scheduler, callback) {}
    }

    override fun getAllSports(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Sports>
    ) {
        sportApiService.getAllSports(apiKey).doApiRequest(scheduler, callback) {}
    }

    override fun getAllLeagues(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>
    ) {
        sportApiService.getAllLeagues(apiKey).doApiRequest(scheduler, callback) {}
    }

    override fun searchAllLeagues(
        scheduler: Scheduler?, apiKey: String,
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    ) {
        sportApiService.searchAllLeagues(apiKey, countryName).doApiRequest(scheduler, callback) {}
    }

    override fun searchAllLeagues(
        scheduler: Scheduler?, apiKey: String,
        countryName: String?,
        sportName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Countrys>
    ) {
        sportApiService.searchAllLeagues(apiKey, countryName, sportName)
            .doApiRequest(scheduler, callback) {}
    }

    override fun searchAllSeasons(
        scheduler: Scheduler?, apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Seasons>
    ) {
        sportApiService.searchAllSeasons(apiKey, idTeam).doApiRequest(scheduler, callback) {}
    }

    override fun searchAllTeam(
        scheduler: Scheduler?, apiKey: String,
        league: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApiService.searchAllTeam(apiKey, league).doApiRequest(scheduler, callback) {}
    }

    override fun searchAllTeam(
        scheduler: Scheduler?, apiKey: String,
        sportName: String?,
        countryName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApiService.searchAllTeam(apiKey, sportName, countryName)
            .doApiRequest(scheduler, callback) {}
    }

    override fun lookupAllTeam(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApiService.lookupAllTeam(apiKey, idLeague).doApiRequest(scheduler, callback) {}
    }

    override fun lookupAllPlayer(
        scheduler: Scheduler?, apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApiService.lookupAllPlayer(apiKey, idTeam).doApiRequest(scheduler, callback) {}
    }

    override fun searchLoves(
        scheduler: Scheduler?, apiKey: String,
        userName: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Users>
    ) {
        sportApiService.searchLoves(apiKey, userName).doApiRequest(scheduler, callback) {}
    }

    override fun lookupLeagues(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Leagues>
    ) {
        sportApiService.lookupLeagues(apiKey, idLeague).doApiRequest(scheduler, callback) {}
    }

    override fun lookupTeam(
        scheduler: Scheduler?, apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Teams>
    ) {
        sportApiService.lookupTeam(apiKey, idTeam).doApiRequest(scheduler, callback) {}
    }

    override fun lookupPlayer(
        scheduler: Scheduler?, apiKey: String,
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Players>
    ) {
        sportApiService.lookupPlayer(apiKey, idPlayer).doApiRequest(scheduler, callback) {}
    }

    override fun lookupEvent(
        scheduler: Scheduler?, apiKey: String,
        idEvent: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.lookupEvent(apiKey, idEvent).doApiRequest(scheduler, callback) {}
    }

    override fun lookupHonour(
        scheduler: Scheduler?, apiKey: String,
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Honors>
    ) {
        sportApiService.lookupHonour(apiKey, idPlayer).doApiRequest(scheduler, callback) {}
    }

    override fun lookupFormerTeam(
        scheduler: Scheduler?, apiKey: String,
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.FormerTeams>
    ) {
        sportApiService.lookupFormerTeam(apiKey, idPlayer).doApiRequest(scheduler, callback) {}
    }

    override fun lookupContract(
        scheduler: Scheduler?, apiKey: String,
        idPlayer: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Contracts>
    ) {
        sportApiService.lookupContract(apiKey, idPlayer).doApiRequest(scheduler, callback) {}
    }

    override fun lookupTable(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Tables>
    ) {
        sportApiService.lookupTable(apiKey, idLeague, season).doApiRequest(scheduler, callback) {}
    }

    override fun eventsNext(
        scheduler: Scheduler?, apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.eventsNext(apiKey, idTeam).doApiRequest(scheduler, callback) {}
    }

    override fun eventsNextLeague(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.eventsNextLeague(apiKey, idLeague).doApiRequest(scheduler, callback) {}
    }

    override fun eventsLast(
        scheduler: Scheduler?, apiKey: String,
        idTeam: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Results>
    ) {
        sportApiService.eventsLast(apiKey, idTeam).doApiRequest(scheduler, callback) {}
    }

    override fun eventsPastLeague(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.eventsPastLeague(apiKey, idLeague).doApiRequest(scheduler, callback) {}
    }

    override fun eventsRound(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        round: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.eventsRound(apiKey, idLeague, round, season)
            .doApiRequest(scheduler, callback) {}
    }

    override fun eventsSeason(
        scheduler: Scheduler?, apiKey: String,
        idLeague: String?,
        season: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.sport.response.Events>
    ) {
        sportApiService.eventsSeason(apiKey, idLeague, season).doApiRequest(scheduler, callback) {}
    }
}