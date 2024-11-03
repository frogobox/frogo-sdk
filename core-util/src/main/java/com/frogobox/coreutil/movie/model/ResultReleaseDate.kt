package com.frogobox.coreutil.movie.model

data class ResultReleaseDate(
    val iso_3166_1: String? = null,
    val release_dates: List<com.frogobox.coreutil.movie.model.ReleaseDate>? = null
)