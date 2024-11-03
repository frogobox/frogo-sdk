package com.frogobox.coreutil.movie.response

data class PeoplePopular(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.PeoplePopularResult>?,
    val total_pages: Int?,
    val total_results: Int?
)