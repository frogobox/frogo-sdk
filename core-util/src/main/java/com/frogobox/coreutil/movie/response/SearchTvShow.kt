package com.frogobox.coreutil.movie.response

data class SearchTvShow(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.SearchTvShowResult>?,
    val total_pages: Int?,
    val total_results: Int?
)