package com.frogobox.coreutil.movie.response

data class TvEpisodeVideos(
    val id: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvEpisodeVideoResult>?
)