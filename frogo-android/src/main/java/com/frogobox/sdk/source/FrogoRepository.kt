package com.frogobox.sdk.source

import com.frogobox.coresdk.source.CoreDataSource
import com.frogobox.sdk.delegate.preference.PreferenceDelegates

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

open class FrogoRepository(
    private val remoteDataSource: FrogoRemoteDataSource? = null,
    private val localDataSource: FrogoLocalDataSource
) : CoreDataSource(), PreferenceDelegates by localDataSource