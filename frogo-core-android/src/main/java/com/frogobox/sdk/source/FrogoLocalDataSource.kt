package com.frogobox.sdk.source

import com.frogobox.coresdk.source.CoreDataSource
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import com.frogobox.sdk.util.AppExecutors


/**
 * Created by faisalamir on 08/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

open class FrogoLocalDataSource(
    private val appExecutors: AppExecutors = AppExecutors(),
    private val preferences: PreferenceDelegates
) : CoreDataSource(), PreferenceDelegates by preferences