package com.frogobox.coresdk.source

import com.google.gson.annotations.SerializedName

/*
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
data class FrogoApiModelPagination<T>(
    val limit: Int,
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int
)