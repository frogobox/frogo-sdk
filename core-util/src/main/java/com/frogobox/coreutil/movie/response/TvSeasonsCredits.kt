package com.frogobox.coreutil.movie.response

data class TvSeasonsCredits(
    val cast: List<com.frogobox.coreutil.movie.model.TvSeasonsCreditsCast>?,
    val crew: List<com.frogobox.coreutil.movie.model.TvSeasonsCreditsCrew>?,
    val id: Int?
)