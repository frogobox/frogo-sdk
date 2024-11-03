package com.frogobox.coreapi.movie


import com.frogobox.coresdk.response.FrogoDataResponse
import io.reactivex.rxjava3.core.Scheduler
import okhttp3.Interceptor

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
interface MovieDataSource {

    // Switch For Using Chuck Interceptor
    fun usingChuckInterceptor(isDebug: Boolean, chuckerInterceptor: Interceptor): MovieDataSource

    // CERTIFICATIONS
    // Get Movie Certifications
    fun getMovieCertifications(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Certifications<com.frogobox.coreutil.movie.model.CertificationMovie>>
    )

    // CERTIFICATIONS
    // Get TV Certifications
    fun getTvCertifications(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Certifications<com.frogobox.coreutil.movie.model.CertificationTv>>
    )

    // ---------------------------------------------------------------------------------------------

    // CHANGES
    // Get Movie Change List
    fun getMovieChangeList(
        scheduler: Scheduler?,
        apiKey: String,
        endDate: String?,
        startDate: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Changes>
    )

    // CHANGES
    // Get TV Change List
    fun getTvChangeList(
        scheduler: Scheduler?,
        apiKey: String,
        endDate: String?,
        startDate: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Changes>
    )

    // CHANGES
    // Get Person Change List
    fun getPersonChangeList(
        scheduler: Scheduler?,
        apiKey: String,
        endDate: String?,
        startDate: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Changes>
    )

    // ---------------------------------------------------------------------------------------------

    // COLLECTION
    // Get Details
    fun getCollectionDetails(
        collection_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.CollectionsDetail>
    )

    // COLLECTION
    // Get Images
    fun getCollectionImages(
        collection_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.CollectionsImage>
    )

    // COLLECTION
    // Get Translations
    fun getCollectionTranslations(
        collection_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.CollectionsTranslation>
    )

    // ---------------------------------------------------------------------------------------------

    // COMPANIES
    // Get Details
    fun getCompaniesDetails(
        company_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.CompaniesDetail>
    )

    // COMPANIES
    // Get Alternative Names
    fun getCompaniesAlternativeName(
        company_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.CompaniesAlternateName>
    )

    // COMPANIES
    // Get Images
    fun getCompaniesImage(
        company_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.CompaniesImage>
    )

    // ---------------------------------------------------------------------------------------------

    // CONFIGURATION
    // Get API Configuration
    fun getConfigurationApi(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.ConfigurationApi>
    )

    // CONFIGURATION
    // Get Countries
    fun getConfigurationCountries(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<List<com.frogobox.coreutil.movie.model.ConfigurationCountry>>
    )

    // CONFIGURATION
    // Get Jobs
    fun getConfigurationJobs(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<List<com.frogobox.coreutil.movie.model.ConfigurationJob>>
    )

    // CONFIGURATION
    // Get Languages
    fun getConfigurationLanguages(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<List<com.frogobox.coreutil.movie.model.ConfigurationLanguage>>
    )

    // CONFIGURATION
    // Get Primary Translations
    fun getConfigurationTranslations(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<List<String>>
    )

    // CONFIGURATION
    // Get Timezones
    fun getConfigurationTimezones(
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<List<com.frogobox.coreutil.movie.model.ConfigurationTimezone>>
    )

    // ---------------------------------------------------------------------------------------------

    // CREDITS
    // Get Details
    fun getCreditsDetails(
        credit_id: String,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Credits>
    )

    // DISCOVER
    // Movie Discover
    fun getDiscoverMovie(
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        region: String?,
        sort_by: String?,
        certification_country: String?,
        certification: String?,
        certification_lte: String?,
        certification_gte: String?,
        include_adult: Boolean?,
        include_video: Boolean?,
        page: Int?,
        primary_release_year: Int?,
        primary_release_date_gte: String?,
        primary_release_date_lte: String?,
        release_date_gte: String?,
        release_date_lte: String?,
        with_release_type: Int?,
        year: Int?,
        vote_count_gte: Int?,
        vote_count_lte: Int?,
        vote_average_gte: Double?,
        vote_average_lte: Double?,
        with_cast: String?,
        with_crew: String?,
        with_people: String?,
        with_companies: String?,
        with_genres: String?,
        without_genres: String?,
        with_keywords: String?,
        without_keywords: String?,
        with_runtime_gte: Double?,
        with_runtime_lte: Double?,
        with_original_language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Discover<com.frogobox.coreutil.movie.model.DiscoverMovie>>

    )

    // ---------------------------------------------------------------------------------------------

    // DISCOVER
    // TV Discover
    fun getDiscoverTv(
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        sort_by: String?,
        air_date_gte: String?,
        air_date_lte: String?,
        first_air_date_gte: String?,
        first_air_date_lte: String?,
        first_air_date_year: Int?,
        page: Int?,
        timezone: String?,
        vote_average_gte: Double?,
        vote_count_gte: Int?,
        with_genres: String?,
        with_networks: String?,
        without_genres: String?,
        with_runtime_gte: Double?,
        with_runtime_lte: Double?,
        include_null_first_air_dates: String?,
        with_original_language: String?,
        without_keywords: String?,
        screened_theatrically: String?,
        with_companies: String?,
        with_keywords: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Discover<com.frogobox.coreutil.movie.model.DiscoverTv>>
    )

    // ---------------------------------------------------------------------------------------------

    // FIND
    // Find by ID
    fun getFindById(
        external_id: String,
        scheduler: Scheduler?, apiKey: String,
        external_source: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Find>
    )

    // ---------------------------------------------------------------------------------------------

    // GENRES
    // Get Movie List
    fun getGenresMovie(
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Genres>
    )

    // GENRES
    // Get TV List
    fun getGenresTv(
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Genres>
    )

    // ---------------------------------------------------------------------------------------------

    // KEYWORDS
    // Get Details
    fun getKeywordsDetail(
        keyword_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.KeywordsDetail>
    )

    // KEYWORDS
    // Get Movies
    fun getKeywordsMovie(
        keyword_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        include_adult: Boolean?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.KeywordsMovies>
    )

    // ---------------------------------------------------------------------------------------------

    // MOVIES
    // Get Details
    fun getMoviesDetails(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        append_to_response: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieDetail>
    )

    // MOVIES
    // Get Account States
    fun getMoviesAccountState(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        session_id: String,
        guest_session_id: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieAccountState>
    )

    // MOVIES
    // Get Alternative Titles
    fun getMoviesAlternativeTitles(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        country: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieAlternativeTitle>
    )

    // MOVIES
    // Get Changes
    fun getMoviesChanges(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        start_date: String?,
        end_date: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieChanges>
    )

    // MOVIES
    // Get Credits
    fun getMoviesCredits(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieCredit>
    )

    // MOVIES
    // Get External Ids
    fun getMoviesExternalIds(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieExternalId>
    )

    // MOVIES
    // Get Images
    fun getMoviesImages(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        include_image_language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieImages>
    )

    // MOVIES
    // Get Keywords
    fun getMoviesKeywords(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieKeywords>
    )

    // MOVIES
    // Get Release Dates
    fun getMoviesReleaseDates(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieReleaseDates>
    )

    // MOVIES
    // Get Videos
    fun getMoviesVideos(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieVideos>
    )

    // MOVIES
    // Get Translations
    fun getMoviesTranslations(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieTranslations>
    )

    // MOVIES
    // Get Recommendations
    fun getMoviesRecommendations(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieRecommendations>
    )

    // MOVIES
    // Get Similar Movies
    fun getMoviesSimilarMovies(
        movie_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieSimilarMovies>
    )

    // MOVIES
    // Get Reviews
    fun getMoviesReviews(
        movie_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieReviews>
    )

    // MOVIES
    // Get Lists
    fun getMoviesLists(
        movie_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieLists>
    )

    // MOVIES
    // Get Latest
    fun getMoviesLatest(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieLatest>
    )

    // MOVIES
    // Get Now Playing
    fun getMoviesNowPlaying(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        region: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieNowPlayings>
    )

    // MOVIES
    // Get Popular
    fun getMoviesPopular(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        region: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MoviePopulars>
    )

    // MOVIES
    // Get Top Rated
    fun getMoviesTopRated(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        region: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieTopRated>
    )

    // MOVIES
    // Get Upcoming
    fun getMoviesUpcoming(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        region: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.MovieUpcoming>
    )

    // ---------------------------------------------------------------------------------------------

    // TRENDING
    // Get Trending All
    fun getTrendingAll(
        media_type: String,
        time_window: String,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingAll>>
    )

    // TRENDING
    // Get Trending Movie
    fun getTrendingMovie(
        media_type: String,
        time_window: String,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingMovie>>
    )

    // TRENDING
    // Get Trending Person
    fun getTrendingPerson(
        media_type: String,
        time_window: String,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingPerson>>
    )

    // TRENDING
    // Get Trending Movie
    fun getTrendingTv(
        media_type: String,
        time_window: String,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Trending<com.frogobox.coreutil.movie.model.TrendingTv>>
    )

    // ---------------------------------------------------------------------------------------------

    // REVIEWS
    // Get Details
    fun getReviews(
        review_id: String,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.Reviews>
    )

    // ---------------------------------------------------------------------------------------------

    // NETWORKS
    // Get Details
    fun getNetworkDetail(
        network_id: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.NetworkDetail>
    )

    // NETWORKS
    // Get Alternative Names
    fun getNetworkAlternativeName(
        network_id: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.NetworkAlternativeName>
    )

    // NETWORKS
    // Get Images
    fun getNetworkImage(
        network_id: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.NetworkImage>
    )

    // ---------------------------------------------------------------------------------------------

    // SEARCH
    // Search Companies
    fun searchCompanies(
        scheduler: Scheduler?, apiKey: String,
        query: String,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.SearchCompanies>
    )

    // SEARCH
    // Search Collections
    fun searchCollections(
        scheduler: Scheduler?, apiKey: String,
        query: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.SearchCollections>
    )

    // SEARCH
    // Search Keywords
    fun searchKeywords(
        scheduler: Scheduler?, apiKey: String,
        query: String,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.SearchKeywords>
    )

    // SEARCH
    // Search Movies
    fun searchMovies(
        scheduler: Scheduler?, apiKey: String,
        query: String,
        language: String?,
        page: Int?,
        include_adult: Boolean?,
        region: String?,
        year: Int?,
        primary_release_year: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.SearchMovies>
    )

    // SEARCH
    // Multi Search
    fun searchMultiSearch(
        scheduler: Scheduler?, apiKey: String,
        query: String,
        language: String?,
        page: Int?,
        include_adult: Boolean?,
        region: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.SearchMulti>
    )

    // SEARCH
    // Search People
    fun searchPeople(
        scheduler: Scheduler?, apiKey: String,
        query: String,
        language: String?,
        page: Int?,
        include_adult: Boolean?,
        region: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.SearchPeople>
    )

    // SEARCH
    // Search Tv Shows
    fun searchTvShows(
        scheduler: Scheduler?, apiKey: String,
        query: String,
        language: String?,
        page: Int?,
        include_adult: Boolean?,
        first_air_date_year: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.SearchMovies>
    )

    // ---------------------------------------------------------------------------------------------

    // TV
    // Get Details
    fun getTvDetails(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        append_to_response: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvDetails>
    )

    // TV
    // Get Account States
    fun getTvAccountStates(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        guest_session_id: String?,
        session_id: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvAccountStates>
    )

    // TV
    // Get Alternative Titles
    fun getTvAlternativeTitles(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvAlternativeTitles>
    )

    // TV
    // Get Changes
    fun getTvChanges(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        startDate: String?,
        endDate: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvChanges>
    )

    // TV
    // Get Content Ratings
    fun getTvContentRatings(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvContentRatings>
    )

    // TV
    // Get Credits
    fun getTvCredits(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvCredits>
    )

    // TV
    // Get Episode Groups
    fun getTvEpisodeGroups(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeGroups>
    )

    // TV
    // Get External IDs
    fun getTvExternalIds(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvExternalIds>
    )

    // TV
    // Get Images
    fun getTvImages(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvImages>
    )

    // TV
    // Get Keyword
    fun getTvKeyword(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvKeywords>
    )

    // TV
    // Get Recommendations
    fun getTvRecommendations(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvRecommendations>
    )

    // TV
    // Get Reviews
    fun getTvReviews(
        tv_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvReviews>
    )

    // TV
    // Get Screened Theatrically
    fun getTvScreenedTheatrically(
        tv_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvScreenedTheatrically>
    )

    // TV
    // Get Similar TV Shows
    fun getTvSimilarTvShows(
        tv_id: Int,
        scheduler: Scheduler?,
        apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvSimilarTVShows>
    )

    // TV
    // Get Translations
    fun getTvTranslations(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvTranslations>
    )

    // TV
    // Get Videos
    fun getTvVideos(
        tv_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvVideos>
    )

    // TV
    // Get Latest
    fun getTvLatest(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvLatest>
    )

    // TV
    // Get TV Airing Today
    fun getTvAiringToday(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvAiringToday>
    )

    // TV
    // Get TV On The Air
    fun getTvOnTheAir(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvOnTheAir>
    )

    // TV
    // Get Popular
    fun getTvPopular(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvPopular>
    )

    // TV
    // Get Top Rated
    fun getTvTopRated(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvTopRated>
    )

    // ---------------------------------------------------------------------------------------------

    // TV SEASONS
    // Get Details
    fun getTvSeasonsDetails(
        tv_id: Int,
        season_number: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        append_to_response: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvSeasonsDetails>
    )

    // TV SEASONS
    // Get Changes
    fun getTvSeasonsChanges(
        season_id: Int,
        scheduler: Scheduler?, apiKey: String,
        startDate: String?,
        endDate: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvSeasonsChanges>
    )

    // TV SEASONS
    // Get Account States
    fun getTvSeasonsAccountStates(
        tv_id: Int,
        season_number: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        guest_session_id: String?,
        session_id: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvSeasonsAccountStates>
    )

    // TV SEASONS
    // Get Credits
    fun getTvSeasonsCredits(
        tv_id: Int,
        season_number: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvSeasonsCredits>
    )

    // TV SEASONS
    // Get External Ids
    fun getTvSeasonsExternalIds(
        tv_id: Int,
        season_number: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvSeasonsExternalIds>
    )

    // TV SEASONS
    // Get Images
    fun getTvSeasonsImages(
        tv_id: Int,
        season_number: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvSeasonsImages>
    )

    // TV SEASONS
    // Get Videos
    fun getTvSeasonsVideos(
        tv_id: Int,
        season_number: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvSeasonsVideos>
    )

    // ---------------------------------------------------------------------------------------------

    // TV EPISODE
    // Get Details
    fun getTvEpisodeDetails(
        tv_id: Int,
        season_number: Int,
        episode_number: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        append_to_response: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeDetails>
    )

    // TV EPISODE
    // Get Changes
    fun getTvEpisodeChanges(
        episode_id: Int,
        scheduler: Scheduler?, apiKey: String,
        startDate: String?,
        endDate: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeChanges>
    )

    // TV EPISODE
    // Get Account States
    fun getTvEpisodeAccountStates(
        tv_id: Int,
        season_number: Int,
        episode_number: Int,
        scheduler: Scheduler?, apiKey: String,
        guest_session_id: String?,
        session_id: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeAccountStates>
    )

    // TV EPISODE
    // Get Credits
    fun getTvEpisodeCredits(
        tv_id: Int,
        season_number: Int,
        episode_number: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeCredits>
    )

    // TV EPISODE
    // Get External IDs
    fun getTvEpisodeExternalIds(
        tv_id: Int,
        season_number: Int,
        episode_number: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeExternalIds>
    )

    // TV EPISODE
    // Get Images
    fun getTvEpisodeImages(
        tv_id: Int,
        season_number: Int,
        episode_number: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeImages>
    )

    // TV EPISODE
    // Get Translations
    fun getTvEpisodeTranslations(
        tv_id: Int,
        season_number: Int,
        episode_number: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.model.TvEpisodeTranslation>
    )

    // TV EPISODE
    // Get Videos
    fun getTvEpisodeVideos(
        tv_id: Int,
        season_number: Int,
        episode_number: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeVideos>
    )

    // ---------------------------------------------------------------------------------------------

    // TV EPISODE GROUPS
    // Get Details
    fun getTvEpisodeGroupsDetails(
        id: String?,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.TvEpisodeGroupsDetails>
    )

    // ---------------------------------------------------------------------------------------------

    // PEOPLE
    // Get Details
    fun getPeopleDetails(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleDetails>
    )

    // PEOPLE
    // Get Changes
    fun getPeopleChanges(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        endDate: String?,
        page: Int?,
        startDate: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleChanges>
    )

    // PEOPLE
    // Get Movie Credits
    fun getPeopleMovieCredits(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleMovieCredits>
    )

    // PEOPLE
    // Get TV Credits
    fun getPeopleTvCredits(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleTvCredits>
    )

    // PEOPLE
    // Get Combined Credits
    fun getPeopleCombinedCredits(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleCombinedCredits>
    )

    // PEOPLE
    // Get External IDs
    fun getPeopleExternalIds(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleExternalIds>
    )

    // PEOPLE
    // Get Images
    fun getPeopleImages(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleImages>
    )

    // PEOPLE
    // Get Tagged Images
    fun getPeopleTaggedImages(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleTaggedImages>
    )

    // PEOPLE
    // Get Translations
    fun getPeopleTranslations(
        person_id: Int,
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleTranslations>
    )

    // PEOPLE
    // Get Latest
    fun getPeopleLatest(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeopleLatest>
    )

    // PEOPLE
    // Get Popular
    fun getPeoplePopular(
        scheduler: Scheduler?, apiKey: String,
        language: String?,
        page: Int?,
        callback: FrogoDataResponse<com.frogobox.coreutil.movie.response.PeoplePopular>
    )

    // ---------------------------------------------------------------------------------------------

    // LIST
    // Get Details
    // fun getListDetails()

    // LIST
    // Check Item Status
    // fun getListCheckItemStatus()

    // METHOD POST AND DELETE ----------------------------------------------------------------------

    // MOVIES
    // POST Rate Movie
    // fun postMoviesRateMovies()

    // MOVIES
    // DELETE delete Rating
    // fun deleteMoviesDeleteRating()

    // TV
    // POST Rate TV Shows
    // fun postTvRateTvShows()

    // TV
    // DELETE Rating
    // fun deleteTvDeleteRating()

    // TV EPISODE
    // POST Rate TV Episode
    // fun postTvEpisodeRateTvEpisode()

    // TV EPISODE
    // DELETE Rating
    // fun deleteTvEpisodeDeleteRating()

    // LIST
    // POST Create List
    // fun postListCreateList()

    // LIST
    // POST Add Movie
    // fun postListAddMovie()

    // LIST
    // POST Remove Movie
    // fun postListRemoveMovie()

    // LIST
    // POST Clear List
    // fun postListClearList()

    // LIST
    // DELETE List
    // fun deleteList

    // ---------------------------------------------------------------------------------------------

}