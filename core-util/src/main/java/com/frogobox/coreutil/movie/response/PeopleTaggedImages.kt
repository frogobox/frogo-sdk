package com.frogobox.coreutil.movie.response

data class PeopleTaggedImages(
    val id: Int?,
    val page: Int?,
    val results: List<com.frogobox.coreutil.movie.model.PeopleTaggedImageResult>?,
    val total_pages: Int?,
    val total_results: Int?
)