package com.frogobox.coreutil.sport.model

import com.frogobox.coreutil.sport.SportData.User.DATE
import com.frogobox.coreutil.sport.SportData.User.ID_EDIT
import com.frogobox.coreutil.sport.SportData.User.ID_EVENT
import com.frogobox.coreutil.sport.SportData.User.ID_LEAGUE
import com.frogobox.coreutil.sport.SportData.User.ID_PLAYER
import com.frogobox.coreutil.sport.SportData.User.ID_TEAM
import com.frogobox.coreutil.sport.SportData.User.STR_EDIT_TYPE
import com.frogobox.coreutil.sport.SportData.User.STR_EVENT
import com.frogobox.coreutil.sport.SportData.User.STR_EVENT_POSTER
import com.frogobox.coreutil.sport.SportData.User.STR_LEAGUE
import com.frogobox.coreutil.sport.SportData.User.STR_PLAYER
import com.frogobox.coreutil.sport.SportData.User.STR_PLAYER_TUMB
import com.frogobox.coreutil.sport.SportData.User.STR_REASON
import com.frogobox.coreutil.sport.SportData.User.STR_TEAM
import com.frogobox.coreutil.sport.SportData.User.STR_TEAM_BADGE
import com.frogobox.coreutil.sport.SportData.User.STR_USERNAME
import com.google.gson.annotations.SerializedName

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TheSportDBApi
 * Copyright (C) 07/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.sport.data.response
 *
 */
data class User(

    @SerializedName(ID_EDIT)
    var idEdit: String? = null,

    @SerializedName(STR_USERNAME)
    var strUsername: String? = null,

    @SerializedName(STR_EDIT_TYPE)
    var strEditType: String? = null,

    @SerializedName(STR_REASON)
    var strReason: String? = null,

    @SerializedName(DATE)
    var date: String? = null,

    @SerializedName(ID_TEAM)
    var idTeam: String? = null,

    @SerializedName(ID_PLAYER)
    var idPlayer: String? = null,

    @SerializedName(ID_LEAGUE)
    var idLeague: String? = null,

    @SerializedName(ID_EVENT)
    var idEvent: String? = null,

    @SerializedName(STR_TEAM)
    var strTeam: String? = null,

    @SerializedName(STR_PLAYER)
    var strPlayer: String? = null,

    @SerializedName(STR_LEAGUE)
    var strLeague: String? = null,

    @SerializedName(STR_EVENT)
    var strEvent: String? = null,

    @SerializedName(STR_EVENT_POSTER)
    var strEventPoster: String? = null,

    @SerializedName(STR_PLAYER_TUMB)
    var strPlayerThumb: String? = null,

    @SerializedName(STR_TEAM_BADGE)
    var strTeamBadge: String? = null

)