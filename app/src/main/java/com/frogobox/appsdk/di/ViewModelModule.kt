package com.frogobox.appsdk.di

import com.frogobox.appsdk.news.NewsViewModel
import com.frogobox.appsdk.news.result.NewsResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
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

val viewModelModule = module {

    viewModel {
        NewsViewModel(get())
    }

    viewModel {
        NewsResultViewModel(get())
    }

}
