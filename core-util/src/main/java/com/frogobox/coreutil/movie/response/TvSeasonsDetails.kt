package com.frogobox.coreutil.movie.response

data class TvSeasonsDetails(
    val _id: String?,
    val air_date: String?,
    val episodes: List<com.frogobox.coreutil.movie.model.TvSeasonsDetailsEpisode>?,
    val id: Int?,
    val name: String?,
    val overview: String?,
    val poster_path: String?,
    val season_number: Int?
)