package com.frogobox.coreutil.sport.model

import com.frogobox.coreutil.sport.SportData.Event.DATE_EVENT
import com.frogobox.coreutil.sport.SportData.Event.DATE_EVENT_LOCAL
import com.frogobox.coreutil.sport.SportData.Event.ID_API_FOOTBALL
import com.frogobox.coreutil.sport.SportData.Event.ID_AWAY_TEAM
import com.frogobox.coreutil.sport.SportData.Event.ID_EVENT
import com.frogobox.coreutil.sport.SportData.Event.ID_HOME_TEAM
import com.frogobox.coreutil.sport.SportData.Event.ID_LEAGUE
import com.frogobox.coreutil.sport.SportData.Event.ID_SOCCER_XML
import com.frogobox.coreutil.sport.SportData.Event.INT_AWAY_SCORE
import com.frogobox.coreutil.sport.SportData.Event.INT_AWAY_SHOTS
import com.frogobox.coreutil.sport.SportData.Event.INT_HOME_SCORE
import com.frogobox.coreutil.sport.SportData.Event.INT_HOME_SHOTS
import com.frogobox.coreutil.sport.SportData.Event.INT_ROUND
import com.frogobox.coreutil.sport.SportData.Event.INT_SPECTATORS
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_FORMATION
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_GOAL_DETAILS
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_LINEUP_DEFENSE
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_LINEUP_FORWARD
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_LINEUP_GOALKEEPER
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_LINEUP_MIDFIELD
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_LINEUP_SUBTITUTES
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_RED_CARDS
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_TEAM
import com.frogobox.coreutil.sport.SportData.Event.STR_AWAY_YELLOW_CARDS
import com.frogobox.coreutil.sport.SportData.Event.STR_BANNER
import com.frogobox.coreutil.sport.SportData.Event.STR_CIRCUIT
import com.frogobox.coreutil.sport.SportData.Event.STR_CITY
import com.frogobox.coreutil.sport.SportData.Event.STR_COUNTRY
import com.frogobox.coreutil.sport.SportData.Event.STR_DATE
import com.frogobox.coreutil.sport.SportData.Event.STR_DESCRIPTION_EN
import com.frogobox.coreutil.sport.SportData.Event.STR_EVENT
import com.frogobox.coreutil.sport.SportData.Event.STR_EVENT_ALTERNATE
import com.frogobox.coreutil.sport.SportData.Event.STR_FANART
import com.frogobox.coreutil.sport.SportData.Event.STR_FILENAME
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_FORMATION
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_GOAL_DETAILS
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_LINEUP_DEFENSE
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_LINEUP_FORWARD
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_LINEUP_GOALKEEPER
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_LINEUP_MIDFIELD
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_LINEUP_SUBTITUTES
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_RED_CARDS
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_TEAM
import com.frogobox.coreutil.sport.SportData.Event.STR_HOME_YELLOW_CARDS
import com.frogobox.coreutil.sport.SportData.Event.STR_LEAGUE
import com.frogobox.coreutil.sport.SportData.Event.STR_LOCKED
import com.frogobox.coreutil.sport.SportData.Event.STR_MAP
import com.frogobox.coreutil.sport.SportData.Event.STR_POSTER
import com.frogobox.coreutil.sport.SportData.Event.STR_RESULT
import com.frogobox.coreutil.sport.SportData.Event.STR_SEASON
import com.frogobox.coreutil.sport.SportData.Event.STR_SPORT
import com.frogobox.coreutil.sport.SportData.Event.STR_THUMB
import com.frogobox.coreutil.sport.SportData.Event.STR_TIME
import com.frogobox.coreutil.sport.SportData.Event.STR_TIME_LOCAL
import com.frogobox.coreutil.sport.SportData.Event.STR_TV_STATION
import com.frogobox.coreutil.sport.SportData.Event.STR_TWEET_1
import com.frogobox.coreutil.sport.SportData.Event.STR_TWEET_2
import com.frogobox.coreutil.sport.SportData.Event.STR_TWEET_3
import com.frogobox.coreutil.sport.SportData.Event.STR_VIDEO
import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TheSportDBApi
 * Copyright (C) 06/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.sport.data.model
 *
 */
data class Event(

    @SerializedName(ID_EVENT)
    var idEvent: String? = null,

    @SerializedName(ID_SOCCER_XML)
    var idSoccerXML: String? = null,

    @SerializedName(ID_API_FOOTBALL)
    var idAPIfootball: String? = null,

    @SerializedName(STR_EVENT)
    var strEvent: String? = null,

    @SerializedName(STR_EVENT_ALTERNATE)
    var strEventAlternate: String? = null,

    @SerializedName(STR_FILENAME)
    var strFilename: String? = null,

    @SerializedName(STR_SPORT)
    var strSport: String? = null,

    @SerializedName(ID_LEAGUE)
    var idLeague: String? = null,

    @SerializedName(STR_LEAGUE)
    var strLeague: String? = null,

    @SerializedName(STR_SEASON)
    var strSeason: String? = null,

    @SerializedName(STR_DESCRIPTION_EN)
    var strDescriptionEN: String? = null,

    @SerializedName(STR_HOME_TEAM)
    var strHomeTeam: String? = null,

    @SerializedName(STR_AWAY_TEAM)
    var strAwayTeam: String? = null,

    @SerializedName(INT_HOME_SCORE)
    var intHomeScore: String? = null,

    @SerializedName(INT_ROUND)
    var intRound: String? = null,

    @SerializedName(INT_AWAY_SCORE)
    var intAwayScore: String? = null,

    @SerializedName(INT_SPECTATORS)
    var intSpectators: String? = null,

    @SerializedName(STR_HOME_GOAL_DETAILS)
    var strHomeGoalDetails: String? = null,

    @SerializedName(STR_HOME_RED_CARDS)
    var strHomeRedCards: String? = null,

    @SerializedName(STR_HOME_YELLOW_CARDS)
    var strHomeYellowCards: String? = null,

    @SerializedName(STR_HOME_LINEUP_GOALKEEPER)
    var strHomeLineupGoalkeeper: String? = null,

    @SerializedName(STR_HOME_LINEUP_DEFENSE)
    var strHomeLineupDefense: String? = null,

    @SerializedName(STR_HOME_LINEUP_MIDFIELD)
    var strHomeLineupMidfield: String? = null,

    @SerializedName(STR_HOME_LINEUP_FORWARD)
    var strHomeLineupForward: String? = null,

    @SerializedName(STR_HOME_LINEUP_SUBTITUTES)
    var strHomeLineupSubstitutes: String? = null,

    @SerializedName(STR_HOME_FORMATION)
    var strHomeFormation: String? = null,

    @SerializedName(STR_AWAY_RED_CARDS)
    var strAwayRedCards: String? = null,

    @SerializedName(STR_AWAY_YELLOW_CARDS)
    var strAwayYellowCards: String? = null,

    @SerializedName(STR_AWAY_GOAL_DETAILS)
    var strAwayGoalDetails: String? = null,

    @SerializedName(STR_AWAY_LINEUP_GOALKEEPER)
    var strAwayLineupGoalkeeper: String? = null,

    @SerializedName(STR_AWAY_LINEUP_DEFENSE)
    var strAwayLineupDefense: String? = null,

    @SerializedName(STR_AWAY_LINEUP_MIDFIELD)
    var strAwayLineupMidfield: String? = null,

    @SerializedName(STR_AWAY_LINEUP_FORWARD)
    var strAwayLineupForward: String? = null,

    @SerializedName(STR_AWAY_LINEUP_SUBTITUTES)
    var strAwayLineupSubstitutes: String? = null,

    @SerializedName(STR_AWAY_FORMATION)
    var strAwayFormation: String? = null,

    @SerializedName(INT_HOME_SHOTS)
    var intHomeShots: String? = null,

    @SerializedName(INT_AWAY_SHOTS)
    var intAwayShots: String? = null,

    @SerializedName(DATE_EVENT)
    var dateEvent: String? = null,

    @SerializedName(DATE_EVENT_LOCAL)
    var dateEventLocal: String? = null,

    @SerializedName(STR_DATE)
    var strDate: String? = null,

    @SerializedName(STR_TIME)
    var strTime: String? = null,

    @SerializedName(STR_TIME_LOCAL)
    var strTimeLocal: String? = null,

    @SerializedName(STR_TV_STATION)
    var strTVStation: String? = null,

    @SerializedName(ID_HOME_TEAM)
    var idHomeTeam: String? = null,

    @SerializedName(ID_AWAY_TEAM)
    var idAwayTeam: String? = null,

    @SerializedName(STR_RESULT)
    var strResult: String? = null,

    @SerializedName(STR_CIRCUIT)
    var strCircuit: String? = null,

    @SerializedName(STR_COUNTRY)
    var strCountry: String? = null,

    @SerializedName(STR_CITY)
    var strCity: String? = null,

    @SerializedName(STR_POSTER)
    var strPoster: String? = null,

    @SerializedName(STR_FANART)
    var strFanart: String? = null,

    @SerializedName(STR_THUMB)
    var strThumb: String? = null,

    @SerializedName(STR_BANNER)
    var strBanner: String? = null,

    @SerializedName(STR_MAP)
    var strMap: String? = null,

    @SerializedName(STR_TWEET_1)
    var strTweet1: String? = null,

    @SerializedName(STR_TWEET_2)
    var strTweet2: String? = null,

    @SerializedName(STR_TWEET_3)
    var strTweet3: String? = null,

    @SerializedName(STR_VIDEO)
    var strVideo: String? = null,

    @SerializedName(STR_LOCKED)
    var strLocked: String? = null

)