package com.frogobox.coreutil.movie.response

data class MovieLists(
    val id: Int? = null,
    val page: Int? = null,
    val results: List<com.frogobox.coreutil.movie.model.MovieListResult>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)