package com.frogobox.coreutil.sport.model


import com.frogobox.coreutil.sport.SportData.Player.DATE_BORN
import com.frogobox.coreutil.sport.SportData.Player.DATE_SIGNED
import com.frogobox.coreutil.sport.SportData.Player.ID_API_FOOTBALL
import com.frogobox.coreutil.sport.SportData.Player.ID_PLAYER
import com.frogobox.coreutil.sport.SportData.Player.ID_PLAYER_MANAGER
import com.frogobox.coreutil.sport.SportData.Player.ID_SOCCER_XML
import com.frogobox.coreutil.sport.SportData.Player.ID_STR_NATIONALITY
import com.frogobox.coreutil.sport.SportData.Player.ID_TEAM
import com.frogobox.coreutil.sport.SportData.Player.ID_TEAM_2
import com.frogobox.coreutil.sport.SportData.Player.ID_TEAM_NATIONAL
import com.frogobox.coreutil.sport.SportData.Player.INT_LOVED
import com.frogobox.coreutil.sport.SportData.Player.INT_SOCCER_XML_TEAM_ID
import com.frogobox.coreutil.sport.SportData.Player.STR_AGENT
import com.frogobox.coreutil.sport.SportData.Player.STR_BANNER
import com.frogobox.coreutil.sport.SportData.Player.STR_BIRTH_LOCATION
import com.frogobox.coreutil.sport.SportData.Player.STR_COLLEGE
import com.frogobox.coreutil.sport.SportData.Player.STR_CREATIVE_COMMONS
import com.frogobox.coreutil.sport.SportData.Player.STR_CUTOUT
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_CN
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_DE
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_EN
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_ES
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_FR
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_HU
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_IL
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_IT
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_JP
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_NL
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_NO
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_PL
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_PT
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_RU
import com.frogobox.coreutil.sport.SportData.Player.STR_DESCRIPTION_SE
import com.frogobox.coreutil.sport.SportData.Player.STR_FACEBOOK
import com.frogobox.coreutil.sport.SportData.Player.STR_FANART_1
import com.frogobox.coreutil.sport.SportData.Player.STR_FANART_2
import com.frogobox.coreutil.sport.SportData.Player.STR_FANART_3
import com.frogobox.coreutil.sport.SportData.Player.STR_FANART_4
import com.frogobox.coreutil.sport.SportData.Player.STR_GENDER
import com.frogobox.coreutil.sport.SportData.Player.STR_HEIGHT
import com.frogobox.coreutil.sport.SportData.Player.STR_INSTAGRAM
import com.frogobox.coreutil.sport.SportData.Player.STR_KIT
import com.frogobox.coreutil.sport.SportData.Player.STR_LOCKED
import com.frogobox.coreutil.sport.SportData.Player.STR_NUMBER
import com.frogobox.coreutil.sport.SportData.Player.STR_OUTFITTER
import com.frogobox.coreutil.sport.SportData.Player.STR_PLAYER
import com.frogobox.coreutil.sport.SportData.Player.STR_POSITION
import com.frogobox.coreutil.sport.SportData.Player.STR_RENDER
import com.frogobox.coreutil.sport.SportData.Player.STR_SIDE
import com.frogobox.coreutil.sport.SportData.Player.STR_SIGNING
import com.frogobox.coreutil.sport.SportData.Player.STR_SPORT
import com.frogobox.coreutil.sport.SportData.Player.STR_TEAM
import com.frogobox.coreutil.sport.SportData.Player.STR_TEAM_2
import com.frogobox.coreutil.sport.SportData.Player.STR_THUMB
import com.frogobox.coreutil.sport.SportData.Player.STR_TWITTER
import com.frogobox.coreutil.sport.SportData.Player.STR_WAGE
import com.frogobox.coreutil.sport.SportData.Player.STR_WEBSITE
import com.frogobox.coreutil.sport.SportData.Player.STR_WEIGHT
import com.frogobox.coreutil.sport.SportData.Player.STR_YOUTUBE
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
data class Player(

    @SerializedName(ID_PLAYER)
    var idPlayer: String? = null,

    @SerializedName(ID_TEAM)
    var idTeam: String? = null,

    @SerializedName(ID_TEAM_2)
    var idTeam2: String? = null,

    @SerializedName(ID_TEAM_NATIONAL)
    var idTeamNational: String? = null,

    @SerializedName(ID_SOCCER_XML)
    var idSoccerXML: String? = null,

    @SerializedName(ID_API_FOOTBALL)
    var idAPIfootball: String? = null,

    @SerializedName(ID_PLAYER_MANAGER)
    var idPlayerManager: String? = null,

    @SerializedName(ID_STR_NATIONALITY)
    var strNationality: String? = null,

    @SerializedName(STR_PLAYER)
    var strPlayer: String? = null,

    @SerializedName(STR_TEAM)
    var strTeam: String? = null,

    @SerializedName(STR_TEAM_2)
    var strTeam2: String? = null,

    @SerializedName(STR_SPORT)
    var strSport: String? = null,

    @SerializedName(INT_SOCCER_XML_TEAM_ID)
    var intSoccerXMLTeamID: String? = null,

    @SerializedName(DATE_BORN)
    var dateBorn: String? = null,

    @SerializedName(STR_NUMBER)
    var strNumber: String? = null,

    @SerializedName(DATE_SIGNED)
    var dateSigned: String? = null,

    @SerializedName(STR_SIGNING)
    var strSigning: String? = null,

    @SerializedName(STR_WAGE)
    var strWage: String? = null,

    @SerializedName(STR_OUTFITTER)
    var strOutfitter: String? = null,

    @SerializedName(STR_KIT)
    var strKit: String? = null,

    @SerializedName(STR_AGENT)
    var strAgent: String? = null,

    @SerializedName(STR_BIRTH_LOCATION)
    var strBirthLocation: String? = null,

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

    @SerializedName(STR_SIDE)
    var strSide: String? = null,

    @SerializedName(STR_POSITION)
    var strPosition: String? = null,

    @SerializedName(STR_COLLEGE)
    var strCollege: String? = null,

    @SerializedName(STR_FACEBOOK)
    var strFacebook: String? = null,

    @SerializedName(STR_WEBSITE)
    var strWebsite: String? = null,

    @SerializedName(STR_TWITTER)
    var strTwitter: String? = null,

    @SerializedName(STR_INSTAGRAM)
    var strInstagram: String? = null,

    @SerializedName(STR_YOUTUBE)
    var strYoutube: String? = null,

    @SerializedName(STR_HEIGHT)
    var strHeight: String? = null,

    @SerializedName(STR_WEIGHT)
    var strWeight: String? = null,

    @SerializedName(INT_LOVED)
    var intLoved: String? = null,

    @SerializedName(STR_THUMB)
    var strThumb: String? = null,

    @SerializedName(STR_CUTOUT)
    var strCutout: String? = null,

    @SerializedName(STR_RENDER)
    var strRender: String? = null,

    @SerializedName(STR_BANNER)
    var strBanner: String? = null,

    @SerializedName(STR_FANART_1)
    var strFanart1: String? = null,

    @SerializedName(STR_FANART_2)
    var strFanart2: String? = null,

    @SerializedName(STR_FANART_3)
    var strFanart3: String? = null,

    @SerializedName(STR_FANART_4)
    var strFanart4: String? = null,

    @SerializedName(STR_CREATIVE_COMMONS)
    var strCreativeCommons: String? = null,

    @SerializedName(STR_LOCKED)
    var strLocked: String? = null

)