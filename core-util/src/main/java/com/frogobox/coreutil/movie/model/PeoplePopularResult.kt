package com.frogobox.coreutil.movie.model

data class PeoplePopularResult(
    val adult: Boolean?,
    val id: Int?,
    val known_for: List<com.frogobox.coreutil.movie.model.PeoplePopularKnownFor>?,
    val name: String?,
    val popularity: Double?,
    val profile_path: String?
)