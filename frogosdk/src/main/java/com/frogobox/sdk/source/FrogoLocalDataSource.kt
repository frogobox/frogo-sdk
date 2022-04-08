package com.frogobox.sdk.source

import com.frogobox.coresdk.source.CoreDataSource
import com.frogobox.coresdk.source.ICoreDataSource
import com.frogobox.sdk.preference.FrogoPreference
import com.frogobox.sdk.util.AppExecutors
import io.reactivex.rxjava3.disposables.Disposable


/*
 * Created by faisalamir on 08/04/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

open class FrogoLocalDataSource(
    private val appExecutors: AppExecutors,
    private val preferences: FrogoPreference
) : CoreDataSource() {


}