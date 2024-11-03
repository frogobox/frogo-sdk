package com.frogobox.coreutil.movie.response

data class TvImages(
    val backdrops: List<com.frogobox.coreutil.movie.model.TvImagesBackdrop>?,
    val id: Int?,
    val posters: List<com.frogobox.coreutil.movie.model.TvImagesPoster>?
)