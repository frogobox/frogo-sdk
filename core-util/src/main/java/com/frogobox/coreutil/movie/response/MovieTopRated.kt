package com.frogobox.coreutil.movie.response

data class MovieTopRated(
    val page: Int? = null,
    val results: List<com.frogobox.coreutil.movie.model.MovieTopRatedResult>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)