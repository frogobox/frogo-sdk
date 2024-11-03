package com.frogobox.coreutil.movie.response

data class SearchKeywords(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.SeacrhKeywordResult>?,
    val total_pages: Int?,
    val total_results: Int?
)