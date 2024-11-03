package com.frogobox.coreutil.pixabay

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PixabayAPI
 * Copyright (C) 14/03/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.pixabay.util
 *
 */
object PixabayConstant {

    // * Required
    const val QUERY_API_KEY = "key"

    // A URL encoded search term. If omitted, all images are returned. This value may not exceed 100 characters.
    // Example: "yellow+flower"
    const val QUERY_Q = "q"

    // Language code of the language to be searched in.
    // Accepted values: cs, da, de, en, es, fr, id, it, hu, nl, no, pl, pt, ro, sk, fi, sv, tr, vi, th, bg, ru, el, ja, ko, zh
    // Default: "en"
    const val QUERY_LANG = "lang"

    // Retrieve individual images by ID.
    const val QUERY_ID = "id"

    // Filter results by image type.
    // Accepted values: "all", "photo", "illustration", "vector"
    // Default: "all"
    const val QUERY_IMAGE_TYPE = "image_type"

    // Filter results by video type.
    // Accepted values: "all", "film", "animation"
    // Default: "all"
    const val QUERY_VIDEO_TYPE = "video_type"

    // Whether an image is wider than it is tall, or taller than it is wide.
    // Accepted values: "all", "horizontal", "vertical"
    // Default: "all"
    const val QUERY_ORIENTATION = "orientation"

    // Filter results by category.
    // Accepted values: backgrounds, fashion, nature, science, education, feelings, health, people, religion, places, animals, industry, computer, food, sports, transportation, travel, buildings, business, music
    const val QUERY_CATEGORY = "category"

    // Minimum image width. Default: "0"
    const val QUERY_MIN_WIDTH = "min_width"

    // Minimum image height. Default: "0"
    const val QUERY_MIN_HEIGHT = "min_height"

    // Filter images by color properties. A comma separated list of values may be used to select multiple properties.
    // Accepted values: "grayscale", "transparent", "red", "orange", "yellow", "green", "turquoise", "blue", "lilac", "pink", "white", "gray", "black", "brown"
    const val QUERY_COLORS = "colors"

    // Select images that have received an Editor's Choice award.
    // Accepted values: "true", "false"
    // Default: "false"
    const val QUERY_EDITORS_CHOICE = "editors_choice"

    // A flag indicating that only images suitable for all ages should be returned.
    // Accepted values: "true", "false"
    // Default: "false"
    const val QUERY_SAFE_SEARCH = "safesearch"

    // How the results should be ordered.
    // Accepted values: "popular", "latest"
    // Default: "popular"
    const val QUERY_ORDER = "order"

    // Returned search results are paginated. Use this parameter to select the page number.
    // Default: 1
    const val QUERY_PAGE = "page"

    // 	Determine the number of results per page.
    // Accepted values: 3 - 200
    // Default: 20
    const val QUERY_PER_PAGE = "per_page"

    // JSONP callback function name
    const val QUERY_CALLBACK = "callback"

    // indent JSON output. This option should not be used in production.
    // Accepted values: "true", "false"
    // Default: "false"
    const val QUERY_PRETTY = "pretty"

}