package com.frogobox.di

import com.frogobox.appadmob.source.AdmobLocalDataSource
import com.frogobox.appadmob.source.AdmobRemoteDataSource
import com.frogobox.appadmob.source.AdmobRepository
import com.frogobox.appsdk.source.AppDatabase
import com.frogobox.appsdk.source.AppLocalDataSource
import com.frogobox.appsdk.source.AppRemoteDataSource
import com.frogobox.appsdk.source.AppRepository
import com.frogobox.appsdk.util.AppConstant.PREF_NAME
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.util.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


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

val repositoryModule = module {

    single {
        PreferenceDelegatesImpl(androidContext(), PREF_NAME)
    }

    single {
        AppDatabase.getInstance(androidContext()).articleDao()
    }

    single {
        AppLocalDataSource(AppExecutors(), get(), get())
    }

    single {
        AppRemoteDataSource(androidContext())
    }

    single {
        AppRepository(androidContext(), get(), get())
    }

    // Please Add Your Code Under This Line --------------------------------------------------------

    single {
        AdmobLocalDataSource(AppExecutors(), get())
    }

    single {
        AdmobRemoteDataSource()
    }

    single {
        AdmobRepository(get(), get())
    }

}