package com.frogobox.coreutil.movie.response

data class TvContentRatings(
    val id: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvContentRatingsResult>?
)