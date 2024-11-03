package com.frogobox.coreutil.movie.response

data class TvRecommendations(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvRecommendationsResult>?,
    val total_pages: Int?,
    val total_results: Int?
)