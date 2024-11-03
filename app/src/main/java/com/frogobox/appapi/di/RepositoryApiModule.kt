package com.frogobox.appapi.di

import com.frogobox.appapi.source.ApiRepository
import org.koin.dsl.module


/*
 * Created by faisalamir on 01/05/22
 * FrogoConsumeApi
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

val repositoryApiModule = module {

    single {
        ApiRepository(get(), get(), get(), get(), get())
    }

}