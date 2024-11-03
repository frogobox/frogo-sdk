package com.frogobox.coreutil.movie.model

data class TvSeasonsDetailsEpisode(
    val air_date: String?,
    val crew: List<com.frogobox.coreutil.movie.model.TvSeasonsDetailsCrew>?,
    val episode_number: Int?,
    val guest_stars: List<com.frogobox.coreutil.movie.model.TvSeasonsDetailsGuestStar>?,
    val id: Int?,
    val name: String?,
    val overview: String?,
    val production_code: String?,
    val season_number: Int?,
    val still_path: String?,
    val vote_average: Double?,
    val vote_count: Int?
)