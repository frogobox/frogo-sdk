package com.frogobox.coreutil.movie.response

data class TvReviews(
    val id: Int?,
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvReviewsResult>?,
    val total_pages: Int?,
    val total_results: Int?
)