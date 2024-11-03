package com.frogobox.coreapi.movie


import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TMDBAPI
 * Copyright (C) 10/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.movie.data.source
 *
 */
interface MovieApiService {

    // CERTIFICATIONS
    // Get Movie Certifications
    @GET(com.frogobox.coreutil.movie.MovieUrl.CERTIFICATION_GET_MOVIE)
    fun getMovieCertifications(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.Certifications<com.frogobox.coreutil.movie.model.CertificationMovie>>

    // CERTIFICATIONS
    // Get TV Certifications
    @GET(com.frogobox.coreutil.movie.MovieUrl.CERTIFICATION_GET_TV)
    fun getTvCertifications(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.Certifications<com.frogobox.coreutil.movie.model.CertificationTv>>

    // ---------------------------------------------------------------------------------------------

    // CHANGES
    // Get Movie Change List
    @GET(com.frogobox.coreutil.movie.MovieUrl.CHANGES_GET_MOVIE)
    fun getMovieChangeList(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_END_DATE) endDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_START_DATE) startDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.Changes>

    // CHANGES
    // Get TV Change List
    @GET(com.frogobox.coreutil.movie.MovieUrl.CHANGES_GET_TV)
    fun getTvChangeList(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_END_DATE) endDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_START_DATE) startDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.Changes>

    // CHANGES
    // Get Person Change List
    @GET(com.frogobox.coreutil.movie.MovieUrl.CHANGES_GET_PERSON)
    fun getPersonChangeList(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_END_DATE) endDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_START_DATE) startDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.Changes>

    // ---------------------------------------------------------------------------------------------

    // COLLECTION
    // Get Collection Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.COLLECTION_GET_DETAIL)
    fun getCollectionDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_COLLECTION_ID) collection_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.CollectionsDetail>

    // COLLECTION
    // Get Collection Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.COLLECTION_GET_IMAGES)
    fun getCollectionImages(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_COLLECTION_ID) collection_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.CollectionsImage>

    // COLLECTION
    // Get Collection Translations
    @GET(com.frogobox.coreutil.movie.MovieUrl.COLLECTION_GET_TRANSLATTIONS)
    fun getCollectionTranslations(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_COLLECTION_ID) collection_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.CollectionsTranslation>

    // ---------------------------------------------------------------------------------------------

    // COMPANIES
    // Get Companies Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.COMPANY_GET_DETAIL)
    fun getCompaniesDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_COMPANY_ID) company_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.CompaniesDetail>

    // COMPANIES
    // Get Companies Alternative Names
    @GET(com.frogobox.coreutil.movie.MovieUrl.COMPANY_GET_ALTERNATIVE_NAME)
    fun getCompaniesAlternativeName(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_COMPANY_ID) company_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.CompaniesAlternateName>

    // COMPANIES
    // Get Companies Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.COMPANY_GET_IMAGE)
    fun getCompaniesImage(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_COMPANY_ID) company_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.CompaniesImage>

    // ---------------------------------------------------------------------------------------------

    // CONFIGURATION
    // Get API Configuration
    @GET(com.frogobox.coreutil.movie.MovieUrl.CONFIGURATION_GET_API_CONFIGURATION)
    fun getConfigurationApi(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.ConfigurationApi>

    // CONFIGURATION
    // Get Countries
    @GET(com.frogobox.coreutil.movie.MovieUrl.CONFIGURATION_GET_COUNTRIES)
    fun getConfigurationCountries(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<List<com.frogobox.coreutil.movie.model.ConfigurationCountry>>

    // CONFIGURATION
    // Get Jobs
    @GET(com.frogobox.coreutil.movie.MovieUrl.CONFIGURATION_GET_JOBS)
    fun getConfigurationJobs(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<List<com.frogobox.coreutil.movie.model.ConfigurationJob>>

    // CONFIGURATION
    // Get Languages
    @GET(com.frogobox.coreutil.movie.MovieUrl.CONFIGURATION_GET_LANGUAGES)
    fun getConfigurationLanguages(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<List<com.frogobox.coreutil.movie.model.ConfigurationLanguage>>

    // CONFIGURATION
    // Get Primary Translations
    @GET(com.frogobox.coreutil.movie.MovieUrl.CONFIGURATION_GET_PRIMARY_TRANSLATIONS)
    fun getConfigurationTranslations(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<List<String>>

    // CONFIGURATION
    // Get Timezones
    @GET(com.frogobox.coreutil.movie.MovieUrl.CONFIGURATION_GET_TIMEZONES)
    fun getConfigurationTimezones(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<List<com.frogobox.coreutil.movie.model.ConfigurationTimezone>>

    // ---------------------------------------------------------------------------------------------

    // CREDITS
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.CREDITS_GET_DETAILS)
    fun getCreditsDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_CREDIT_ID) credit_id: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.Credits>

    // ---------------------------------------------------------------------------------------------

    // DISCOVER
    // Movie Discover
    @GET(com.frogobox.coreutil.movie.MovieUrl.DISCOVER_GET_MOVIE)
    fun getDiscoverMovie(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_REGION) region: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_SORT_BY) sort_by: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_CERTIFICATION_COUNTRY) certification_country: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_CERTIFICATION) certification: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_CERTIFICATION_LTE) certification_lte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_CERTIFICATION_GTE) certification_gte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_ADULT) include_adult: Boolean?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_VIDEO) include_video: Boolean?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PRIMARY_RELEASE_YEAR) primary_release_year: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PRIMARY_RELEASE_GTE) primary_release_date_gte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PRIMARY_RELEASE_LTE) primary_release_date_lte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_RELEASE_DATE_GTE) release_date_gte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_RELEASE_DATE_LTE) release_date_lte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_RELEASE_DATE_TYPE) with_release_type: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_YEAR) year: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_VOTE_COUNT_GTE) vote_count_gte: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_VOTE_COUNT_LTE) vote_count_lte: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_VOTE_AVERAGE_GTE) vote_average_gte: Double?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_VOTE_COUNT_LTE) vote_average_lte: Double?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_CAST) with_cast: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_CREW) with_crew: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_PEOPLE) with_people: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_COMPANIES) with_companies: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_GENRES) with_genres: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITHOUT_GENRES) without_genres: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_KEYWORDS) with_keywords: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITHOUT_KEYWORDS) without_keywords: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_RUNTIME_GTE) with_runtime_gte: Double?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_RUNTIME_LTE) with_runtime_lte: Double?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_ORIGINAL_LANGUAGE) with_original_language: String?
    ): Observable<com.frogobox.coreutil.movie.response.Discover<com.frogobox.coreutil.movie.model.DiscoverMovie>>

    // DISCOVER
    // TV Discover
    @GET(com.frogobox.coreutil.movie.MovieUrl.DISCOVER_GET_TV)
    fun getDiscoverTv(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_SORT_BY) sort_by: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_AIR_DATE_GTE) air_date_gte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_AIR_DATE_LTE) air_date_lte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_FIRST_AIR_DATE_GTE) first_air_date_gte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_FIRST_AIR_DATE_LTE) first_air_date_lte: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_FIRST_AIR_DATE_YEAR) first_air_date_year: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_TIMEZONE) timezone: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_VOTE_AVERAGE_GTE) vote_average_gte: Double?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_VOTE_COUNT_GTE) vote_count_gte: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_GENRES) with_genres: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_NETWORKS) with_networks: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITHOUT_GENRES) without_genres: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_RUNTIME_GTE) with_runtime_gte: Double?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_RUNTIME_LTE) with_runtime_lte: Double?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_NULL_FIRST_AIR_DATES) include_null_first_air_dates: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_ORIGINAL_LANGUAGE) with_original_language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_KEYWORDS) without_keywords: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_SCREENED_THEATRICALLY) screened_theatrically: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_COMPANIES) with_companies: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_WITH_KEYWORDS) with_keywords: String?
    ): Observable<com.frogobox.coreutil.movie.response.Discover<com.frogobox.coreutil.movie.model.DiscoverTv>>

    // ---------------------------------------------------------------------------------------------

    // FIND
    // Find by ID
    @GET(com.frogobox.coreutil.movie.MovieUrl.FIND_GET_ID)
    fun getFindById(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EXTERNAL_ID) external_id: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_EXTERNAL_SOURCE) external_source: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.Find>

    // ---------------------------------------------------------------------------------------------

    // GENRES
    // Get Movie List
    @GET(com.frogobox.coreutil.movie.MovieUrl.GENRES_GET_MOVIES)
    fun getGenresMovie(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.Genres>

    // GENRES
    // Get TV List
    @GET(com.frogobox.coreutil.movie.MovieUrl.GENRES_GET_TV)
    fun getGenresTv(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.Genres>

    // ---------------------------------------------------------------------------------------------

    // KEYWORDS
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.KEYWORDS_GET_DETAILS)
    fun getKeywordsDetail(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_KEYWORD_ID) keyword_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.KeywordsDetail>

    // KEYWORDS
    // Get Movies
    @GET(com.frogobox.coreutil.movie.MovieUrl.KEYWORDS_GET_MOVIES)
    fun getKeywordsMovie(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_KEYWORD_ID) keyword_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_ADULT) include_adult: Boolean?
    ): Observable<com.frogobox.coreutil.movie.response.KeywordsMovies>

    // ---------------------------------------------------------------------------------------------

    // MOVIES
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_DETAILS)
    fun getMoviesDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_APPEND_TO_RESPONSE) append_to_response: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieDetail>

    // MOVIES
    // Get Account States
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_ACCOUNT_STATES)
    fun getMoviesAccountState(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_SESSION_ID) session_id: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_GUEST_SESSION_ID) guest_session_id: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieAccountState>

    // MOVIES
    // Get Alternative Titles
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_ALTERNATIVE_TITLE)
    fun getMoviesAlternativeTitles(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_COUNTRY) country: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieAlternativeTitle>

    // MOVIES
    // Get Changes
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_CHANGES)
    fun getMoviesChanges(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_START_DATE) start_date: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_END_DATE) end_date: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.MovieChanges>

    // MOVIES
    // Get Credits
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_CREDITS)
    fun getMoviesCredits(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.MovieCredit>

    // MOVIES
    // Get External Ids
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_EXTERNAL_IDS)
    fun getMoviesExternalIds(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.MovieExternalId>

    // MOVIES
    // Get Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_IMAGES)
    fun getMoviesImages(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_IMAGE_LANGUAGE) include_image_language: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieImages>

    // MOVIES
    // Get Keywords
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_KEYWORDS)
    fun getMoviesKeywords(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.MovieKeywords>

    // MOVIES
    // Get Release Dates
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_RELEASE_DATE)
    fun getMoviesReleaseDates(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.MovieReleaseDates>

    // MOVIES
    // Get Videos
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_VIDEOS)
    fun getMoviesVideos(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieVideos>

    // MOVIES
    // Get Translations
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_TRANSLATIONS)
    fun getMoviesTranslations(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.MovieTranslations>

    // MOVIES
    // Get Recommendations
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_RECOMENDATIONS)
    fun getMoviesRecommendations(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.MovieRecommendations>

    // MOVIES
    // Get Similar Movies
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_SIMILAR_MOVIES)
    fun getMoviesSimilarMovies(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.MovieSimilarMovies>

    // MOVIES
    // Get Reviews
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_REVIEWS)
    fun getMoviesReviews(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.MovieReviews>

    // MOVIES
    // Get Lists
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_LIST)
    fun getMoviesLists(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MOVIE_ID) movie_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.MovieLists>

    // MOVIES
    // Get Latest
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_LATEST)
    fun getMoviesLatest(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieLatest>

    // MOVIES
    // Get Now Playing
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_NOW_PLAYING)
    fun getMoviesNowPlaying(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_REGION) region: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieNowPlayings>

    // MOVIES
    // Get Popular
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_POPULAR)
    fun getMoviesPopular(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_REGION) region: String?
    ): Observable<com.frogobox.coreutil.movie.response.MoviePopulars>

    // MOVIES
    // Get Top Rated
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_TOP_RATED)
    fun getMoviesTopRated(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_REGION) region: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieTopRated>

    // MOVIES
    // Get Upcoming
    @GET(com.frogobox.coreutil.movie.MovieUrl.MOVIES_GET_UPCOMING)
    fun getMoviesUpcoming(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_REGION) region: String?
    ): Observable<com.frogobox.coreutil.movie.response.MovieUpcoming>

    // ---------------------------------------------------------------------------------------------

    // TRENDING
    // Get Trending All
    @GET(com.frogobox.coreutil.movie.MovieUrl.TRENDING_GET_TREND)
    fun getTrendingAll(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MEDIA_TYPE) media_type: String,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TIME_WINDOW) time_window: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingAll>>

    // TRENDING
    // Get Trending Movie
    @GET(com.frogobox.coreutil.movie.MovieUrl.TRENDING_GET_TREND)
    fun getTrendingMovie(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MEDIA_TYPE) media_type: String,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TIME_WINDOW) time_window: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingMovie>>

    // TRENDING
    // Get Trending Person
    @GET(com.frogobox.coreutil.movie.MovieUrl.TRENDING_GET_TREND)
    fun getTrendingPerson(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MEDIA_TYPE) media_type: String,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TIME_WINDOW) time_window: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingPerson>>

    // TRENDING
    // Get Trending Movie
    @GET(com.frogobox.coreutil.movie.MovieUrl.TRENDING_GET_TREND)
    fun getTrendingTv(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_MEDIA_TYPE) media_type: String,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TIME_WINDOW) time_window: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingTv>>

    // ---------------------------------------------------------------------------------------------

    // REVIEWS
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.REVIEWS_GET_DETAILS)
    fun getReviews(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_REVIEW_ID) review_id: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.Reviews>

    // ---------------------------------------------------------------------------------------------

    // NETWORKS
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.NETWORKS_GET_DETAILS)
    fun getNetworkDetail(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_NETWORK_ID) network_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.NetworkDetail>

    // NETWORKS
    // Get Alternative Names
    @GET(com.frogobox.coreutil.movie.MovieUrl.NETWORKS_GET_ALTERNATIVE_NAMES)
    fun getNetworkAlternativeName(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_NETWORK_ID) network_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.NetworkAlternativeName>

    // NETWORKS
    // Get Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.NETWORKS_GET_IMAGES)
    fun getNetworkImage(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_NETWORK_ID) network_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.NetworkImage>

    // ---------------------------------------------------------------------------------------------

    // SEARCH
    // Search Companies
    @GET(com.frogobox.coreutil.movie.MovieUrl.SEARCH_GET_COMPANIES)
    fun searchCompanies(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_QUERY) query: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.SearchCompanies>

    // SEARCH
    // Search Collections
    @GET(com.frogobox.coreutil.movie.MovieUrl.SEARCH_GET_COLLECTIONS)
    fun searchCollections(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_QUERY) query: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.SearchCollections>

    // SEARCH
    // Search Keywords
    @GET(com.frogobox.coreutil.movie.MovieUrl.SEARCH_GET_KEYWORDS)
    fun searchKeywords(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_QUERY) query: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.SearchKeywords>

    // SEARCH
    // Search Movies
    @GET(com.frogobox.coreutil.movie.MovieUrl.SEARCH_GET_MOVIES)
    fun searchMovies(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_QUERY) query: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_ADULT) include_adult: Boolean?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_REGION) region: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_YEAR) year: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PRIMARY_RELEASE_YEAR) primary_release_year: Int?
    ): Observable<com.frogobox.coreutil.movie.response.SearchMovies>

    // SEARCH
    // Multi Search
    @GET(com.frogobox.coreutil.movie.MovieUrl.SEARCH_GET_MULTI_SEARCH)
    fun searchMultiSearch(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_QUERY) query: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_ADULT) include_adult: Boolean?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_REGION) region: String?
    ): Observable<com.frogobox.coreutil.movie.response.SearchMulti>

    // SEARCH
    // Search People
    @GET(com.frogobox.coreutil.movie.MovieUrl.SEARCH_GET_SEARCH_PEOPLE)
    fun searchPeople(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_QUERY) query: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_ADULT) include_adult: Boolean?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_REGION) region: String?
    ): Observable<com.frogobox.coreutil.movie.response.SearchPeople>

    // SEARCH
    // Search Tv Shows
    @GET(com.frogobox.coreutil.movie.MovieUrl.SEARCH_GET_TV_SHOWS)
    fun searchTvShows(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_QUERY) query: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_INCLUDE_ADULT) include_adult: Boolean?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_FIRST_AIR_DATE_YEAR) first_air_date_year: Int?
    ): Observable<com.frogobox.coreutil.movie.response.SearchMovies>

    // ---------------------------------------------------------------------------------------------

    // TV
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_DETAILS)
    fun getTvDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_APPEND_TO_RESPONSE) append_to_response: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvDetails>

    // TV
    // Get Account States
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_ACCOUNT_STATES)
    fun getTvAccountStates(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_GUEST_SESSION_ID) guest_session_id: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_SESSION_ID) session_id: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvAccountStates>

    // TV
    // Get Alternative Titles
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_ALTERNATIVE_TITLES)
    fun getTvAlternativeTitles(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvAlternativeTitles>

    // TV
    // Get Changes
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_CHANGES)
    fun getTvChanges(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_START_DATE) startDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_END_DATE) endDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvChanges>

    // TV
    // Get Content Ratings
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_CONTENT_RATINGS)
    fun getTvContentRatings(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvContentRatings>

    // TV
    // Get Credits
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_CREDITS)
    fun getTvCredits(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvCredits>

    // TV
    // Get Episode Groups
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_EPISODE_GROUPS)
    fun getTvEpisodeGroups(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeGroups>

    // TV
    // Get External IDs
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_EXTERNAL_IDS)
    fun getTvExternalIds(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvExternalIds>

    // TV
    // Get Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_IMAGES)
    fun getTvImages(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvImages>

    // TV
    // Get Keyword
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_KEYWORDS)
    fun getTvKeyword(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.TvKeywords>

    // TV
    // Get Recommendations
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_RECOMMENDATIONS)
    fun getTvRecommendations(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvRecommendations>

    // TV
    // Get Reviews
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_REVIEWS)
    fun getTvReviews(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.TvReviews>

    // TV
    // Get Screened Theatrically
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_SCREENED_THEATRICALLY)
    fun getTvScreenedTheatrically(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.TvScreenedTheatrically>

    // TV
    // Get Similar TV Shows
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_SIMILIAR_TV_SHOWS)
    fun getTvSimilarTvShows(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvSimilarTVShows>

    // TV
    // Get Translations
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_TRANSLATIONS)
    fun getTvTranslations(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.TvTranslations>

    // TV
    // Get Videos
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_VIDEOS)
    fun getTvVideos(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvVideos>

    // TV
    // Get Latest
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_LATEST)
    fun getTvLatest(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvLatest>

    // TV
    // Get TV Airing Today
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_TV_AIRING_TODAY)
    fun getTvAiringToday(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvAiringToday>

    // TV
    // Get TV On The Air
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_TV_ON_THE_AIR)
    fun getTvOnTheAir(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvOnTheAir>

    // TV
    // Get Popular
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_POPULAR)
    fun getTvPopular(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvPopular>

    // TV
    // Get Top Rated
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_GET_TOP_RATED)
    fun getTvTopRated(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvTopRated>

    // ---------------------------------------------------------------------------------------------

    // TV SEASONS
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_SEASONS_GET_DETAILS)
    fun getTvSeasonsDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_APPEND_TO_RESPONSE) append_to_response: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvSeasonsDetails>

    // TV SEASONS
    // Get Changes
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_SEASONS_GET_CHANGES)
    fun getTvSeasonsChanges(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_ID) season_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_START_DATE) startDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_END_DATE) endDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvSeasonsChanges>

    // TV SEASONS
    // Get Account States
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_SEASONS_GET_ACCOUNT_STATES)
    fun getTvSeasonsAccountStates(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_GUEST_SESSION_ID) guest_session_id: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_SESSION_ID) session_id: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvSeasonsAccountStates>

    // TV SEASONS
    // Get Credits
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_SEASONS_GET_CREDITS)
    fun getTvSeasonsCredits(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvSeasonsCredits>

    // TV SEASONS
    // Get External Ids
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_SEASONS_GET_EXTERNAL_IDS)
    fun getTvSeasonsExternalIds(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvSeasonsExternalIds>

    // TV SEASONS
    // Get Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_SEASONS_GET_IMAGES)
    fun getTvSeasonsImages(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvSeasonsImages>

    // TV SEASONS
    // Get Videos
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_SEASONS_GET_VIDEOS)
    fun getTvSeasonsVideos(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvSeasonsVideos>

    // ---------------------------------------------------------------------------------------------

    // TV EPISODE
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_DETAILS)
    fun getTvEpisodeDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EPISODE_NUMBER) episode_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_APPEND_TO_RESPONSE) append_to_response: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeDetails>

    // TV EPISODE
    // Get Changes
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_CHANGES)
    fun getTvEpisodeChanges(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EPISODE_ID) episode_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_START_DATE) startDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_END_DATE) endDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeChanges>

    // TV EPISODE
    // Get Account States
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_ACCOUNT_STATES)
    fun getTvEpisodeAccountStates(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EPISODE_NUMBER) episode_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_GUEST_SESSION_ID) guest_session_id: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_SESSION_ID) session_id: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeAccountStates>

    // TV EPISODE
    // Get Credits
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_CREDITS)
    fun getTvEpisodeCredits(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EPISODE_NUMBER) episode_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeCredits>

    // TV EPISODE
    // Get External IDs
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_EXTERNAL_IDS)
    fun getTvEpisodeExternalIds(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EPISODE_NUMBER) episode_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeExternalIds>

    // TV EPISODE
    // Get Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_IMAGES)
    fun getTvEpisodeImages(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EPISODE_NUMBER) episode_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeImages>

    // TV EPISODE
    // Get Translations
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_TRANSLATIONS)
    fun getTvEpisodeTranslations(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EPISODE_NUMBER) episode_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.model.TvEpisodeTranslation>

    // TV EPISODE
    // Get Videos
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_VIDEOS)
    fun getTvEpisodeVideos(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_TV_ID) tv_id: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_SEASON_NUMBER) season_number: Int,
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_EPISODE_NUMBER) episode_number: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeVideos>

    // ---------------------------------------------------------------------------------------------

    // TV EPISODE GROUPS
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.TV_EPISODES_GET_GROUPS_DETAIL)
    fun getTvEpisodeGroupsDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_ID) id: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.TvEpisodeGroupsDetails>

    // ---------------------------------------------------------------------------------------------

    // PEOPLE
    // Get Details
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_DETAILS)
    fun getPeopleDetails(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleDetails>

    // PEOPLE
    // Get Changes
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_CHANGES)
    fun getPeopleChanges(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_END_DATE) endDate: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_START_DATE) startDate: String?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleChanges>

    // PEOPLE
    // Get Movie Credits
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_MOVIE_CREDITS)
    fun getPeopleMovieCredits(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleMovieCredits>

    // PEOPLE
    // Get TV Credits
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_TV_CREDITS)
    fun getPeopleTvCredits(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleTvCredits>

    // PEOPLE
    // Get Combined Credits
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_COMBINED_CREDITS)
    fun getPeopleCombinedCredits(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleCombinedCredits>

    // PEOPLE
    // Get External IDs
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_EXTERNAL_IDS)
    fun getPeopleExternalIds(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleExternalIds>

    // PEOPLE
    // Get Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_IMAGES)
    fun getPeopleImages(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String
    ): Observable<com.frogobox.coreutil.movie.response.PeopleImages>

    // PEOPLE
    // Get Tagged Images
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_TAGGED_IMAGES)
    fun getPeopleTaggedImages(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleTaggedImages>

    // PEOPLE
    // Get Translations
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_TRANSLATIONS)
    fun getPeopleTranslations(
        @Path(com.frogobox.coreutil.movie.MovieConstant.PATH_PERSON_ID) person_id: Int,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleTranslations>

    // PEOPLE
    // Get Latest
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_LATEST)
    fun getPeopleLatest(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?
    ): Observable<com.frogobox.coreutil.movie.response.PeopleLatest>

    // PEOPLE
    // Get Popular
    @GET(com.frogobox.coreutil.movie.MovieUrl.PEOPLE_GET_POPULAR)
    fun getPeoplePopular(
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_API_KEY) apiKey: String,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_LANGUAGE) language: String?,
        @Query(com.frogobox.coreutil.movie.MovieConstant.QUERY_PAGE) page: Int?
    ): Observable<com.frogobox.coreutil.movie.response.PeoplePopular>

}