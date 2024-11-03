package com.frogobox.coreutil.movie.response

data class TvKeywords(
    val id: Int?,
    val results: List<com.frogobox.coreutil.movie.model.TvKeywordsResult>?
)