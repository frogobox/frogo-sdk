package com.frogobox.coreutil.movie.response

data class MovieRecommendations(
    val page: Int? = null,
    val results: List<com.frogobox.coreutil.movie.model.MovieRecommendationResult>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)