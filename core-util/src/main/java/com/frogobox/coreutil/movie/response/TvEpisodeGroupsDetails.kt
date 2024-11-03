package com.frogobox.coreutil.movie.response

data class TvEpisodeGroupsDetails(
    val description: String?,
    val episode_count: Int?,
    val group_count: Int?,
    val groups: List<com.frogobox.coreutil.movie.model.TvEpisodeGroupsDetailGroup>?,
    val id: String?,
    val name: String?,
    val network: com.frogobox.coreutil.movie.model.TvEpisodeGroupsDetailNetwork?,
    val type: Int?
)