package com.frogobox.coreutil.sport.model

import com.frogobox.coreutil.sport.SportData.Country.DATE_FIRST_EVENT
import com.frogobox.coreutil.sport.SportData.Country.ID_API_FOOTBALL
import com.frogobox.coreutil.sport.SportData.Country.ID_CUP
import com.frogobox.coreutil.sport.SportData.Country.ID_LEAGUE
import com.frogobox.coreutil.sport.SportData.Country.ID_SOCCEL_XML
import com.frogobox.coreutil.sport.SportData.Country.INT_FORMED_YEAR
import com.frogobox.coreutil.sport.SportData.Country.STR_BADGE
import com.frogobox.coreutil.sport.SportData.Country.STR_BANNER
import com.frogobox.coreutil.sport.SportData.Country.STR_COMPLETE
import com.frogobox.coreutil.sport.SportData.Country.STR_COUNTRY
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_CN
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_DE
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_EN
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_ES
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_FR
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_HU
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_IL
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_IT
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_JP
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_NL
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_NO
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_PL
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_PT
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_RU
import com.frogobox.coreutil.sport.SportData.Country.STR_DESCRIPTION_SE
import com.frogobox.coreutil.sport.SportData.Country.STR_DIVISION
import com.frogobox.coreutil.sport.SportData.Country.STR_FACEBOOK
import com.frogobox.coreutil.sport.SportData.Country.STR_FANART_1
import com.frogobox.coreutil.sport.SportData.Country.STR_FANART_2
import com.frogobox.coreutil.sport.SportData.Country.STR_FANART_3
import com.frogobox.coreutil.sport.SportData.Country.STR_FANART_4
import com.frogobox.coreutil.sport.SportData.Country.STR_GENDER
import com.frogobox.coreutil.sport.SportData.Country.STR_LEAGUE
import com.frogobox.coreutil.sport.SportData.Country.STR_LEAGUE_ALTERNATE
import com.frogobox.coreutil.sport.SportData.Country.STR_LOCKED
import com.frogobox.coreutil.sport.SportData.Country.STR_LOGO
import com.frogobox.coreutil.sport.SportData.Country.STR_NAMING
import com.frogobox.coreutil.sport.SportData.Country.STR_POSTER
import com.frogobox.coreutil.sport.SportData.Country.STR_RSS
import com.frogobox.coreutil.sport.SportData.Country.STR_SPORT
import com.frogobox.coreutil.sport.SportData.Country.STR_TROPHY
import com.frogobox.coreutil.sport.SportData.Country.STR_TWITTER
import com.frogobox.coreutil.sport.SportData.Country.STR_WEBSITE
import com.frogobox.coreutil.sport.SportData.Country.STR_YOUTUBE
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
data class Country(

    @SerializedName(ID_LEAGUE)
    var idLeague: String? = null,

    @SerializedName(ID_SOCCEL_XML)
    var idSoccerXML: String? = null,

    @SerializedName(ID_API_FOOTBALL)
    var idAPIfootball: String? = null,

    @SerializedName(STR_SPORT)
    var strSport: String? = null,

    @SerializedName(STR_LEAGUE)
    var strLeague: String? = null,

    @SerializedName(STR_LEAGUE_ALTERNATE)
    var strLeagueAlternate: String? = null,

    @SerializedName(STR_DIVISION)
    var idLeastrDivisiongue: String? = null,

    @SerializedName(ID_CUP)
    var idCup: String? = null,

    @SerializedName(INT_FORMED_YEAR)
    var intFormedYear: String? = null,

    @SerializedName(DATE_FIRST_EVENT)
    var dateFirstEvent: String? = null,

    @SerializedName(STR_GENDER)
    var strGender: String? = null,

    @SerializedName(STR_COUNTRY)
    var strCountry: String? = null,

    @SerializedName(STR_WEBSITE)
    var strWebsite: String? = null,

    @SerializedName(STR_FACEBOOK)
    var strFacebook: String? = null,

    @SerializedName(STR_TWITTER)
    var strTwitter: String? = null,

    @SerializedName(STR_YOUTUBE)
    var strYoutube: String? = null,

    @SerializedName(STR_RSS)
    var strRSS: String? = null,

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

    @SerializedName(STR_FANART_1)
    var strFanart1: String? = null,

    @SerializedName(STR_FANART_2)
    var strFanart2: String? = null,

    @SerializedName(STR_FANART_3)
    var strFanart3: String? = null,

    @SerializedName(STR_FANART_4)
    var strFanart4: String? = null,

    @SerializedName(STR_BANNER)
    var strBanner: String? = null,

    @SerializedName(STR_BADGE)
    var strBadge: String? = null,

    @SerializedName(STR_LOGO)
    var strLogo: String? = null,

    @SerializedName(STR_POSTER)
    var strPoster: String? = null,

    @SerializedName(STR_TROPHY)
    var strTrophy: String? = null,

    @SerializedName(STR_NAMING)
    var strNaming: String? = null,

    @SerializedName(STR_COMPLETE)
    var strComplete: String? = null,

    @SerializedName(STR_LOCKED)
    var strLocked: String? = null

)