package com.frogobox.coreutil.movie.response

data class SearchCompanies(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.SearchCompanyResult>?,
    val total_pages: Int?,
    val total_results: Int?
)