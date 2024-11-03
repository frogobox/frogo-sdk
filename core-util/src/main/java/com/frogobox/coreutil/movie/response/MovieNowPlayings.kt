package com.frogobox.coreutil.movie.response

data class MovieNowPlayings(
    val dates: com.frogobox.coreutil.movie.model.MovieNowPlayingDates? = null,
    val page: Int? = null,
    val results: List<com.frogobox.coreutil.movie.model.MovieNowPlayingResult>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)