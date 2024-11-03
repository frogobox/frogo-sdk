package com.frogobox.coreutil.movie.response

data class TvPopular(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvPopularResult>?,
    val total_pages: Int?,
    val total_results: Int?
)