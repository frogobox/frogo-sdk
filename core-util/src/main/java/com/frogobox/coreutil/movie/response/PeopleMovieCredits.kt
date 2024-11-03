package com.frogobox.coreutil.movie.response

data class PeopleMovieCredits(
    val cast: List<com.frogobox.coreutil.movie.model.PeopleMovieCreditCast>?,
    val crew: List<com.frogobox.coreutil.movie.model.PeopleMovieCreditCrew>?,
    val id: Int?
)