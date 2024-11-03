package com.frogobox.coreutil.sport.model

import com.frogobox.coreutil.sport.SportData.Team.ID_API_FOOTBALL
import com.frogobox.coreutil.sport.SportData.Team.ID_LEAGUE
import com.frogobox.coreutil.sport.SportData.Team.ID_SOCCER_XML
import com.frogobox.coreutil.sport.SportData.Team.ID_TEAM
import com.frogobox.coreutil.sport.SportData.Team.INT_FORMED_YEAR
import com.frogobox.coreutil.sport.SportData.Team.INT_LOVED
import com.frogobox.coreutil.sport.SportData.Team.STR_ALTERNATE
import com.frogobox.coreutil.sport.SportData.Team.STR_COUNTRY
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_CN
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_DE
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_EN
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_ES
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_FR
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_HU
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_IL
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_IT
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_JP
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_NL
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_NO
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_PL
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_PT
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_RU
import com.frogobox.coreutil.sport.SportData.Team.STR_DESCRIPTION_SE
import com.frogobox.coreutil.sport.SportData.Team.STR_DIVISION
import com.frogobox.coreutil.sport.SportData.Team.STR_FACEBOOK
import com.frogobox.coreutil.sport.SportData.Team.STR_GENDER
import com.frogobox.coreutil.sport.SportData.Team.STR_INSTAGRAM
import com.frogobox.coreutil.sport.SportData.Team.STR_KEYWORD
import com.frogobox.coreutil.sport.SportData.Team.STR_LEAGUE
import com.frogobox.coreutil.sport.SportData.Team.STR_LOCKED
import com.frogobox.coreutil.sport.SportData.Team.STR_MANAGER
import com.frogobox.coreutil.sport.SportData.Team.STR_RSS
import com.frogobox.coreutil.sport.SportData.Team.STR_SPORT
import com.frogobox.coreutil.sport.SportData.Team.STR_STADIUM
import com.frogobox.coreutil.sport.SportData.Team.STR_STADIUM_CAPACITY
import com.frogobox.coreutil.sport.SportData.Team.STR_STADIUM_DESCRIPTION
import com.frogobox.coreutil.sport.SportData.Team.STR_STADIUM_LOCATION
import com.frogobox.coreutil.sport.SportData.Team.STR_STADIUM_THUMB
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_BADGE
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_BANNER
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_FANART_1
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_FANART_2
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_FANART_3
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_FANART_4
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_JERSEY
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_LOGO
import com.frogobox.coreutil.sport.SportData.Team.STR_TEAM_SHORT
import com.frogobox.coreutil.sport.SportData.Team.STR_TWITTER
import com.frogobox.coreutil.sport.SportData.Team.STR_WEBSITE
import com.frogobox.coreutil.sport.SportData.Team.STR_YOUTUBE
import com.google.gson.annotations.SerializedName

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
 * com.frogobox.frogoconsumeapi.sport.model.data
 *
 */
data class Team(

    @SerializedName(ID_TEAM)
    var idTeam: String? = null,

    @SerializedName(ID_SOCCER_XML)
    var idSoccerXML: String? = null,

    @SerializedName(ID_API_FOOTBALL)
    var idAPIfootball: String? = null,

    @SerializedName(INT_LOVED)
    var intLoved: String? = null,

    @SerializedName(STR_TEAM)
    var strTeam: String? = null,

    @SerializedName(STR_TEAM_SHORT)
    var strTeamShort: String? = null,

    @SerializedName(STR_ALTERNATE)
    var strAlternate: String? = null,

    @SerializedName(INT_FORMED_YEAR)
    var intFormedYear: String? = null,

    @SerializedName(STR_SPORT)
    var strSport: String? = null,

    @SerializedName(STR_LEAGUE)
    var strLeague: String? = null,

    @SerializedName(ID_LEAGUE)
    var idLeague: String? = null,

    @SerializedName(STR_DIVISION)
    var strDivision: String? = null,

    @SerializedName(STR_MANAGER)
    var strManager: String? = null,

    @SerializedName(STR_STADIUM)
    var strStadium: String? = null,

    @SerializedName(STR_KEYWORD)
    var strKeywords: String? = null,

    @SerializedName(STR_RSS)
    var strRSS: String? = null,

    @SerializedName(STR_STADIUM_THUMB)
    var strStadiumThumb: String? = null,

    @SerializedName(STR_STADIUM_DESCRIPTION)
    var strStadiumDescription: String? = null,

    @SerializedName(STR_STADIUM_LOCATION)
    var strStadiumLocation: String? = null,

    @SerializedName(STR_STADIUM_CAPACITY)
    var intStadiumCapacity: String? = null,

    @SerializedName(STR_WEBSITE)
    var strWebsite: String? = null,

    @SerializedName(STR_FACEBOOK)
    var strFacebook: String? = null,

    @SerializedName(STR_TWITTER)
    var strTwitter: String? = null,

    @SerializedName(STR_INSTAGRAM)
    var strInstagram: String? = null,

    @SerializedName(STR_DESCRIPTION_EN)
    var strDescriptionEN: String? = null,

    @SerializedName(STR_DESCRIPTION_DE)
    var strDescriptionDE: String? = null,

    @SerializedName(STR_DESCRIPTION_FR)
    var strDescriptionFR: String? = null,

    @SerializedName(STR_DESCRIPTION_IT)
    var strDescriptionIT: String? = null,

    @SerializedName(STR_DESCRIPTION_CN)
    var strDescriptionCN: String? = null,

    @SerializedName(STR_DESCRIPTION_JP)
    var strDescriptionJP: String? = null,

    @SerializedName(STR_DESCRIPTION_RU)
    var strDescriptionRU: String? = null,

    @SerializedName(STR_DESCRIPTION_ES)
    var strDescriptionES: String? = null,

    @SerializedName(STR_DESCRIPTION_PT)
    var strDescriptionPT: String? = null,

    @SerializedName(STR_DESCRIPTION_SE)
    var strDescriptionSE: String? = null,

    @SerializedName(STR_DESCRIPTION_NL)
    var strDescriptionNL: String? = null,

    @SerializedName(STR_DESCRIPTION_HU)
    var strDescriptionHU: String? = null,

    @SerializedName(STR_DESCRIPTION_NO)
    var strDescriptionNO: String? = null,

    @SerializedName(STR_DESCRIPTION_PL)
    var strDescriptionPL: String? = null,

    @SerializedName(STR_DESCRIPTION_IL)
    var strDescriptionIL: String? = null,

    @SerializedName(STR_GENDER)
    var strGender: String? = null,

    @SerializedName(STR_COUNTRY)
    var strCountry: String? = null,

    @SerializedName(STR_TEAM_BADGE)
    var strTeamBadge: String? = null,

    @SerializedName(STR_TEAM_JERSEY)
    var strTeamJersey: String? = null,

    @SerializedName(STR_TEAM_LOGO)
    var strTeamLogo: String? = null,

    @SerializedName(STR_TEAM_FANART_1)
    var strTeamFanart1: String? = null,

    @SerializedName(STR_TEAM_FANART_2)
    var strTeamFanart2: String? = null,

    @SerializedName(STR_TEAM_FANART_3)
    var strTeamFanart3: String? = null,

    @SerializedName(STR_TEAM_FANART_4)
    var strTeamFanart4: String? = null,

    @SerializedName(STR_TEAM_BANNER)
    var strTeamBanner: String? = null,

    @SerializedName(STR_YOUTUBE)
    var strYoutube: String? = null,

    @SerializedName(STR_LOCKED)
    var strLocked: String? = null

)