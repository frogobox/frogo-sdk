package com.frogobox.coreutil.movie.model

data class TvEpisodeGroupsDetailGroup(
    val episodes: List<com.frogobox.coreutil.movie.model.TvEpisodeGroupsDetailEpisode>?,
    val id: String?,
    val locked: Boolean?,
    val name: String?,
    val order: Int?
)