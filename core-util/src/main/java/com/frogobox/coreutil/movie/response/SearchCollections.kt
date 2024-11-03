package com.frogobox.coreutil.movie.response

data class SearchCollections(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.SearchCollectionResult>?,
    val total_pages: Int?,
    val total_results: Int?
)