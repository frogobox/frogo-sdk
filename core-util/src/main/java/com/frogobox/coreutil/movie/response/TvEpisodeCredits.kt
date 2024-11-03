package com.frogobox.coreutil.movie.response

data class TvEpisodeCredits(
    val cast: List<com.frogobox.coreutil.movie.model.TvEpisodeAccountStateCast>?,
    val crew: List<com.frogobox.coreutil.movie.model.TvEpisodeCreditCrew>?,
    val guest_stars: List<com.frogobox.coreutil.movie.model.TvEpisodeCreditGuestStar>?,
    val id: Int?
)