package com.frogobox.coreutil.movie.response

data class PeopleCombinedCredits(
    val cast: List<com.frogobox.coreutil.movie.model.PeopleCombinedCreditCast>?,
    val crew: List<com.frogobox.coreutil.movie.model.PeopleCombinedCreditCrew>?,
    val id: Int?
)