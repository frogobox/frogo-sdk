package com.frogobox.coreutil.movie.response

data class MovieLatest(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val belongs_to_collection: String? = null,
    val budget: Int? = null,
    val genres: List<com.frogobox.coreutil.movie.model.MovieLatestGenre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<Any>? = null,
    val production_countries: List<Any>? = null,
    val release_date: String? = null,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<Any>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Int? = null,
    val vote_count: Int? = null
)