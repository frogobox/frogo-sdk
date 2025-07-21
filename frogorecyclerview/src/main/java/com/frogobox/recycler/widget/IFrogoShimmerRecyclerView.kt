package com.frogobox.recycler.widget

import com.frogobox.recycler.core.FrogoSingleRv
import com.frogobox.recycler.core.FrogoSingleSrv

/**
 * Created by Faisal Amir on 02/06/2020
 * FrogoRecyclerView Source Code
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2020 FrogoBox Inc.      
 * All rights reserved
 *
 */
 
interface IFrogoShimmerRecyclerView {

    // Setup SrvSingletonShimmer
    fun defineShimmerView(): FrogoSingleSrv

    // Setup SrvSingletonRecycler
    fun <T> defineRecyclerView(): FrogoSingleRv<T>

    // Start Shimmer Animation
    fun startShimmer()

    // Stop Shimmer Animation
    fun stopShimmer()

}