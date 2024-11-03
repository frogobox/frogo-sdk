package com.frogobox.coreutil.movie.response

data class SearchMulti(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.SearchMultiResult>?,
    val total_pages: Int?,
    val total_results: Int?
)