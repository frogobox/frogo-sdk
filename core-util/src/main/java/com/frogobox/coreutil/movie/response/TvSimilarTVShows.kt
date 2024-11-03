package com.frogobox.coreutil.movie.response

data class TvSimilarTVShows(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvSimilarTVShowsResult>?,
    val total_pages: Int?,
    val total_results: Int?
)