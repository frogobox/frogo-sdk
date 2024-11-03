package com.frogobox.coreutil.movie.response

data class TvCredits(
    val cast: List<com.frogobox.coreutil.movie.model.TvCreditsCast>?,
    val crew: List<com.frogobox.coreutil.movie.model.TvCreditsCrew>?,
    val id: Int?
)