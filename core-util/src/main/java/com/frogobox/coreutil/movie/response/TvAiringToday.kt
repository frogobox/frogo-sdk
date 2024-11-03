package com.frogobox.coreutil.movie.response

data class TvAiringToday(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvAiringTodayResult>?,
    val total_pages: Int?,
    val total_results: Int?
)