package com.frogobox.coreutil.movie.response

data class PeopleTvCredits(
    val cast: List<com.frogobox.coreutil.movie.model.PeopleTvCreditCast>?,
    val crew: List<com.frogobox.coreutil.movie.model.PeopleTvCreditCrew>?,
    val id: Int?
)