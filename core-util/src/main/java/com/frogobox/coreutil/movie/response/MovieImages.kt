package com.frogobox.coreutil.movie.response

data class MovieImages(
    val backdrops: List<com.frogobox.coreutil.movie.model.MovieBackdrop>? = null,
    val id: Int? = null,
    val posters: List<com.frogobox.coreutil.movie.model.MoviePoster>? = null
)