package com.frogobox.appadmob.di

import com.frogobox.appadmob.source.AdmobLocalDataSource
import com.frogobox.appadmob.source.AdmobRemoteDataSource
import com.frogobox.appadmob.source.AdmobRepository
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.util.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


/**
 * Created by faisalamir on 19/04/22
 * FrogoAdmob
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

val repositoryAdsModule = module {

    single {
        PreferenceDelegatesImpl(androidContext(), "com_frogobox_appadmob")
    }

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