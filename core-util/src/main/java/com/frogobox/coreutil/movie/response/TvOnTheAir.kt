package com.frogobox.coreutil.movie.response

data class TvOnTheAir(
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvOnTheAirResult>?,
    val total_pages: Int?,
    val total_results: Int?
)