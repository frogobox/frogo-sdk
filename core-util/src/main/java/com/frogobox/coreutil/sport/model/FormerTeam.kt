package com.frogobox.coreutil.sport.model

import com.frogobox.coreutil.sport.SportData.FormerTeam.ID
import com.frogobox.coreutil.sport.SportData.FormerTeam.ID_FORMER_TEAM
import com.frogobox.coreutil.sport.SportData.FormerTeam.ID_PLAYER
import com.frogobox.coreutil.sport.SportData.FormerTeam.STR_DEPARTED
import com.frogobox.coreutil.sport.SportData.FormerTeam.STR_FORMER_TEAM
import com.frogobox.coreutil.sport.SportData.FormerTeam.STR_JOINED
import com.frogobox.coreutil.sport.SportData.FormerTeam.STR_PLAYER
import com.frogobox.coreutil.sport.SportData.FormerTeam.STR_SPORT
import com.frogobox.coreutil.sport.SportData.FormerTeam.STR_TEAM_BADGE
import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TheSportDBApi
 * Copyright (C) 08/03/2020.
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
data class FormerTeam(

    @SerializedName(ID)
    var id: String? = null,

    @SerializedName(ID_PLAYER)
    var idPlayer: String? = null,

    @SerializedName(ID_FORMER_TEAM)
    var idFormerTeam: String? = null,

    @SerializedName(STR_SPORT)
    var strSport: String? = null,

    @SerializedName(STR_PLAYER)
    var strPlayer: String? = null,

    @SerializedName(STR_FORMER_TEAM)
    var strFormerTeam: String? = null,

    @SerializedName(STR_TEAM_BADGE)
    var strTeamBadge: String? = null,

    @SerializedName(STR_JOINED)
    var strJoined: String? = null,

    @SerializedName(STR_DEPARTED)
    var strDeparted: String? = null

)