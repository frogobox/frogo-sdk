package com.frogobox.coreutil.movie.model

data class PeopleTaggedImageResult(
    val aspect_ratio: Double?,
    val file_path: String?,
    val height: Int?,
    val id: String?,
    val image_type: String?,
    val iso_639_1: String?,
    val media: com.frogobox.coreutil.movie.model.PeopleTaggedImageMedia?,
    val media_type: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val width: Int?
)