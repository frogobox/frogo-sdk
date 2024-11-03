package com.frogobox.coreutil.movie.response

data class SearchMovies(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.SearchMovieResult>?,
    val total_pages: Int?,
    val total_results: Int?
)