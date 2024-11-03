package com.frogobox.coreutil.movie.response

data class MovieReviews(
    val id: Int? = null,
    val page: Int? = null,
    val results: List<com.frogobox.coreutil.movie.model.MovieReviewResult>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)