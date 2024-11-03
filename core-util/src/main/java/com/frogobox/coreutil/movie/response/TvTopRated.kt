package com.frogobox.coreutil.movie.response

data class TvTopRated(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvTopRatedResult>?,
    val total_pages: Int?,
    val total_results: Int?
)