package com.frogobox.coreutil.movie.response

data class TvAccountStates(
    val favorite: Boolean?,
    val id: Int?,
    val rated: com.frogobox.coreutil.movie.model.TvAccountStatesRated?,
    val watchlist: Boolean?
)