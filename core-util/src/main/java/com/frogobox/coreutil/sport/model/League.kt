package com.frogobox.coreutil.sport.model

import com.frogobox.coreutil.sport.SportData.League.ID_LEAGUE
import com.frogobox.coreutil.sport.SportData.League.STR_LEAGUE
import com.frogobox.coreutil.sport.SportData.League.STR_LEAGUE_ALTERNATE
import com.frogobox.coreutil.sport.SportData.League.STR_SPORT
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
data class League(

    @SerializedName(ID_LEAGUE)
    var idLeague: String? = null,

    @SerializedName(STR_LEAGUE)
    var strLeague: String? = null,

    @SerializedName(STR_SPORT)
    var strSport: String? = null,

    @SerializedName(STR_LEAGUE_ALTERNATE)
    var strLeagueAlternate: String? = null

)