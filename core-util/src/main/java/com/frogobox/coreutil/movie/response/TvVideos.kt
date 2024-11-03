package com.frogobox.coreutil.movie.response

data class TvVideos(
    val id: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvVideosResult>?
)