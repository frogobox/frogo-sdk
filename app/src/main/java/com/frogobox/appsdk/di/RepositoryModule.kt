package com.frogobox.appsdk.di

import com.frogobox.appsdk.source.AppLocalDataSource
import com.frogobox.appsdk.source.AppRemoteDataSource
import com.frogobox.appsdk.source.AppRepository
import com.frogobox.sdk.preference.FrogoPreference
import com.frogobox.sdk.util.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


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

val repositoryModule = module {

    single {
        FrogoPreference(androidContext(), "ANJAYY")
    }

    single {
        AppLocalDataSource(AppExecutors(), get())
    }

    single {
        AppRemoteDataSource(androidContext())
    }

    single {
        AppRepository(get(), get())
    }

    // Please Add Your Code Under This Line --------------------------------------------------------

}