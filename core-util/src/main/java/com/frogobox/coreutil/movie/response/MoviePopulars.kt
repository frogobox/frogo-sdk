package com.frogobox.coreutil.movie.response

data class MoviePopulars(
    val page: Int? = null,
    val results: List<com.frogobox.coreutil.movie.model.MoviePopularResult>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)