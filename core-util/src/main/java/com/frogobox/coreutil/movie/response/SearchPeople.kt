package com.frogobox.coreutil.movie.response

data class SearchPeople(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.SearchPeopleResult>?,
    val total_pages: Int?,
    val total_results: Int?
)