package com.frogobox.coreutil.movie

/**
 * Created by Faisal Amir
 * =========================================
 * Copyright (C) 10/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 *
 */
object MovieUrl {

    const val BASE_URL = "https://api.themoviedb.org/"

    const val API_KEY = "5e922c3d4b1b0e96fffcc6b0b395878f"
    const val VERSION_API_3 = "3"
    const val VERSION_API_4 = "4"

    const val BASE_URL_IMAGE_ORIGNAL = "https://image.tmdb.org/t/p/original"
    const val BASE_URL_IMAGE_W500 = "https://image.tmdb.org/t/p/w500"

    // CERTIFICATION
    const val CERTIFICATION_GET_MOVIE = "$VERSION_API_3/certification/movie/list"
    const val CERTIFICATION_GET_TV = "$VERSION_API_3/certification/tv/list"

    // CHANGES
    const val CHANGES_GET_MOVIE = "$VERSION_API_3/movie/changes"
    const val CHANGES_GET_TV = "$VERSION_API_3/tv/changes"
    const val CHANGES_GET_PERSON = "$VERSION_API_3/person/changes"

    // COLLECTION
    const val COLLECTION_GET_DETAIL = "$VERSION_API_3/collection/{collection_id}"
    const val COLLECTION_GET_IMAGES = "$VERSION_API_3/collection/{collection_id}/images"
    const val COLLECTION_GET_TRANSLATTIONS =
        "$VERSION_API_3/collection/{collection_id}/translations"

    // COMPANIES
    const val COMPANY_GET_DETAIL = "$VERSION_API_3/company/{company_id}"
    const val COMPANY_GET_ALTERNATIVE_NAME =
        "$VERSION_API_3/company/{company_id}/alternative_names"
    const val COMPANY_GET_IMAGE = "$VERSION_API_3/company/{company_id}/images"

    // CONFIGURATION
    const val CONFIGURATION_GET_API_CONFIGURATION = "$VERSION_API_3/configuration"
    const val CONFIGURATION_GET_COUNTRIES = "$VERSION_API_3/configuration/countries"
    const val CONFIGURATION_GET_JOBS = "$VERSION_API_3/configuration/jobs"
    const val CONFIGURATION_GET_LANGUAGES = "$VERSION_API_3/configuration/languages"
    const val CONFIGURATION_GET_PRIMARY_TRANSLATIONS =
        "$VERSION_API_3/configuration/primary_translations"
    const val CONFIGURATION_GET_TIMEZONES = "$VERSION_API_3/configuration/timezones"

    // CREDITS
    const val CREDITS_GET_DETAILS = "$VERSION_API_3/credit/{credit_id}"

    // DISCOVER
    const val DISCOVER_GET_MOVIE = "$VERSION_API_3/discover/movie"
    const val DISCOVER_GET_TV = "$VERSION_API_3/discover/tv"

    // FIND
    const val FIND_GET_ID = "$VERSION_API_3/find/{external_id}"

    // GENRES
    const val GENRES_GET_MOVIES = "$VERSION_API_3/genre/movie/list"
    const val GENRES_GET_TV = "$VERSION_API_3/genre/tv/list"

    // GUEST SESSIONS
    const val GUEST_GET_SESSIONS_RATED_MOVIES =
        "$VERSION_API_3/guest_session/{guest_session_id}/rated/movies"
    const val GUEST_GET_SESSIONS_RATED_TV_SHOWS =
        "$VERSION_API_3/guest_session/{guest_session_id}/rated/tv"
    const val GUEST_GET_SESSIONS_RATED_TV_EPISODE =
        "$VERSION_API_3/guest_session/{guest_session_id}/rated/tv/episodes"

    // KEYWORDS
    const val KEYWORDS_GET_DETAILS = "$VERSION_API_3/keyword/{keyword_id}"
    const val KEYWORDS_GET_MOVIES = "$VERSION_API_3/keyword/{keyword_id}/movies"

    // LISTS
    const val LISTS_GET_DETAILS = "$VERSION_API_3/list/{list_id}"
    const val LISTS_GET_ITEM_STATUS = "$VERSION_API_3/list/{list_id}/item_status"

    // MOVIES
    const val MOVIES_GET_DETAILS = "$VERSION_API_3/movie/{movie_id}"
    const val MOVIES_GET_ACCOUNT_STATES = "$VERSION_API_3/movie/{movie_id}/account_states"
    const val MOVIES_GET_ALTERNATIVE_TITLE =
        "$VERSION_API_3/movie/{movie_id}/alternative_titles"
    const val MOVIES_GET_CHANGES = "$VERSION_API_3/movie/{movie_id}/changes"
    const val MOVIES_GET_CREDITS = "$VERSION_API_3/movie/{movie_id}/credits"
    const val MOVIES_GET_EXTERNAL_IDS = "$VERSION_API_3/movie/{movie_id}/external_ids"
    const val MOVIES_GET_IMAGES = "$VERSION_API_3/movie/{movie_id}/images"
    const val MOVIES_GET_KEYWORDS = "$VERSION_API_3/movie/{movie_id}/keywords"
    const val MOVIES_GET_RELEASE_DATE = "$VERSION_API_3/movie/{movie_id}/release_dates"
    const val MOVIES_GET_VIDEOS = "$VERSION_API_3/movie/{movie_id}/videos"
    const val MOVIES_GET_TRANSLATIONS = "$VERSION_API_3/movie/{movie_id}/translations"
    const val MOVIES_GET_RECOMENDATIONS = "$VERSION_API_3/movie/{movie_id}/recommendations"
    const val MOVIES_GET_SIMILAR_MOVIES = "$VERSION_API_3/movie/{movie_id}/similar"
    const val MOVIES_GET_REVIEWS = "$VERSION_API_3/movie/{movie_id}/reviews"
    const val MOVIES_GET_LIST = "$VERSION_API_3/movie/{movie_id}/lists"
    const val MOVIES_POST_RATE_MOVIE = "$VERSION_API_3/movie/{movie_id}/rating"
    const val MOVIES_DELETE_RATING = "$VERSION_API_3/movie/{movie_id}/rating"
    const val MOVIES_GET_LATEST = "$VERSION_API_3/movie/latest"
    const val MOVIES_GET_NOW_PLAYING = "$VERSION_API_3/movie/now_playing"
    const val MOVIES_GET_POPULAR = "$VERSION_API_3/movie/popular"
    const val MOVIES_GET_TOP_RATED = "$VERSION_API_3/movie/top_rated"
    const val MOVIES_GET_UPCOMING = "$VERSION_API_3/movie/upcoming"

    // NETWORKS
    const val NETWORKS_GET_DETAILS = "$VERSION_API_3/network/{network_id}"
    const val NETWORKS_GET_ALTERNATIVE_NAMES =
        "$VERSION_API_3/network/{network_id}/alternative_names"
    const val NETWORKS_GET_IMAGES = "$VERSION_API_3/network/{network_id}/images"

    // TRENDING
    const val TRENDING_GET_TREND = "$VERSION_API_3/trending/{media_type}/{time_window}"

    // PEOPLE
    const val PEOPLE_GET_DETAILS = "$VERSION_API_3/person/{person_id}"
    const val PEOPLE_GET_CHANGES = "$VERSION_API_3/person/{person_id}/changes"
    const val PEOPLE_GET_MOVIE_CREDITS = "$VERSION_API_3/person/{person_id}/movie_credits"
    const val PEOPLE_GET_TV_CREDITS = "$VERSION_API_3/person/{person_id}/tv_credits"
    const val PEOPLE_GET_COMBINED_CREDITS = "$VERSION_API_3/person/{person_id}/combined_credits"
    const val PEOPLE_GET_EXTERNAL_IDS = "$VERSION_API_3/person/{person_id}/external_ids"
    const val PEOPLE_GET_IMAGES = "$VERSION_API_3/person/{person_id}/images"
    const val PEOPLE_GET_TAGGED_IMAGES = "$VERSION_API_3/person/{person_id}/tagged_images"
    const val PEOPLE_GET_TRANSLATIONS = "$VERSION_API_3/person/{person_id}/translations"
    const val PEOPLE_GET_LATEST = "$VERSION_API_3/person/latest"
    const val PEOPLE_GET_POPULAR = "$VERSION_API_3/person/popular"

    // REVIEWS
    const val REVIEWS_GET_DETAILS = "$VERSION_API_3/review/{review_id}"

    // SEARCH
    const val SEARCH_GET_COMPANIES = "$VERSION_API_3/search/company"
    const val SEARCH_GET_COLLECTIONS = "$VERSION_API_3/search/collection"
    const val SEARCH_GET_KEYWORDS = "$VERSION_API_3/search/keyword"
    const val SEARCH_GET_MOVIES = "$VERSION_API_3/search/movie"
    const val SEARCH_GET_MULTI_SEARCH = "$VERSION_API_3/search/multi"
    const val SEARCH_GET_SEARCH_PEOPLE = "$VERSION_API_3/search/person"
    const val SEARCH_GET_TV_SHOWS = "$VERSION_API_3/search/tv"

    // TV
    const val TV_GET_DETAILS = "$VERSION_API_3/tv/{tv_id}"
    const val TV_GET_ACCOUNT_STATES = "$VERSION_API_3/tv/{tv_id}/account_states"
    const val TV_GET_ALTERNATIVE_TITLES = "$VERSION_API_3/tv/{tv_id}/alternative_titles"
    const val TV_GET_CHANGES = "$VERSION_API_3/tv/{tv_id}/changes"
    const val TV_GET_CONTENT_RATINGS = "$VERSION_API_3/tv/{tv_id}/content_ratings"
    const val TV_GET_CREDITS = "$VERSION_API_3/tv/{tv_id}/credits"
    const val TV_GET_EPISODE_GROUPS = "$VERSION_API_3/tv/{tv_id}/episode_groups"
    const val TV_GET_EXTERNAL_IDS = "$VERSION_API_3/tv/{tv_id}/external_ids"
    const val TV_GET_IMAGES = "$VERSION_API_3/tv/{tv_id}/images"
    const val TV_GET_KEYWORDS = "$VERSION_API_3/tv/{tv_id}/keywords"
    const val TV_GET_RECOMMENDATIONS = "$VERSION_API_3/tv/{tv_id}/recommendations"
    const val TV_GET_REVIEWS = "$VERSION_API_3/tv/{tv_id}/reviews"
    const val TV_GET_SCREENED_THEATRICALLY = "$VERSION_API_3/tv/{tv_id}/screened_theatrically"
    const val TV_GET_SIMILIAR_TV_SHOWS = "$VERSION_API_3/tv/{tv_id}/similar"
    const val TV_GET_TRANSLATIONS = "$VERSION_API_3/tv/{tv_id}/translations"
    const val TV_GET_VIDEOS = "$VERSION_API_3/tv/{tv_id}/videos"
    const val TV_POST_RATE_TV_SHOW = "$VERSION_API_3/tv/{tv_id}/rating"
    const val TV_DELETE_RATING = "$VERSION_API_3/tv/{tv_id}/rating"
    const val TV_GET_LATEST = "$VERSION_API_3/tv/latest"
    const val TV_GET_TV_AIRING_TODAY = "$VERSION_API_3/tv/airing_today"
    const val TV_GET_TV_ON_THE_AIR = "$VERSION_API_3/tv/on_the_air"
    const val TV_GET_POPULAR = "$VERSION_API_3/tv/popular"
    const val TV_GET_TOP_RATED = "$VERSION_API_3/tv/top_rated"

    // TV SEASONS
    const val TV_SEASONS_GET_DETAILS = "$VERSION_API_3/tv/{tv_id}/season/{season_number}"
    const val TV_SEASONS_GET_CHANGES = "$VERSION_API_3/tv/season/{season_id}/changes"
    const val TV_SEASONS_GET_ACCOUNT_STATES =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/account_states"
    const val TV_SEASONS_GET_CREDITS =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/credits"
    const val TV_SEASONS_GET_EXTERNAL_IDS =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/external_ids"
    const val TV_SEASONS_GET_IMAGES = "$VERSION_API_3/tv/{tv_id}/season/{season_number}/images"
    const val TV_SEASONS_GET_VIDEOS = "$VERSION_API_3/tv/{tv_id}/season/{season_number}/videos"

    // TV EPISODES
    const val TV_EPISODES_GET_DETAILS =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}"
    const val TV_EPISODES_GET_CHANGES = "$VERSION_API_3/tv/episode/{episode_id}/changes"
    const val TV_EPISODES_GET_ACCOUNT_STATES =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}/account_states"
    const val TV_EPISODES_GET_CREDITS =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}/credits"
    const val TV_EPISODES_GET_EXTERNAL_IDS =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}/external_ids"
    const val TV_EPISODES_GET_IMAGES =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}/images"
    const val TV_EPISODES_GET_TRANSLATIONS =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}/translations"
    const val TV_EPISODES_POST_RATE_TV_EPISODE =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating"
    const val TV_EPISODES_DELETE_RATING =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}/rating"
    const val TV_EPISODES_GET_VIDEOS =
        "$VERSION_API_3/tv/{tv_id}/season/{season_number}/episode/{episode_number}/videos"

    // TV EPISODES GROUPS
    const val TV_EPISODES_GET_GROUPS_DETAIL = "$VERSION_API_3/tv/episode_group/{id}"

}