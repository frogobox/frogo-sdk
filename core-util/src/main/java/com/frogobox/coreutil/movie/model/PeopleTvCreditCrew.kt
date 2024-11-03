package com.frogobox.coreutil.movie.model

data class PeopleTvCreditCrew(
    val backdrop_path: String?,
    val credit_id: String?,
    val department: String?,
    val episode_count: Int?,
    val first_air_date: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val job: String?,
    val name: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val vote_average: Double?,
    val vote_count: Int?
)