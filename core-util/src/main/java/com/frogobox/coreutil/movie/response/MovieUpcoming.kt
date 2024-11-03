package com.frogobox.coreutil.movie.response

data class MovieUpcoming(
    val dates: com.frogobox.coreutil.movie.model.MovieUpcomingDates?,
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.MovieUpcomingResult>?,
    val total_pages: Int?,
    val total_results: Int?
)