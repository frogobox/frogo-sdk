package com.frogobox.sdk.ext

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.sdk.preference.FrogoPreference
import com.frogobox.sdk.util.FrogoPagerHelper
import com.frogobox.sdk.util.FrogoPagerHelper2


/*
 * Created by faisalamir on 18/04/22
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

fun AppCompatActivity.getViewPagerAdapter(): FrogoPagerHelper {
    return FrogoPagerHelper(this.supportFragmentManager)
}

// -------------------------------------------------------------------------------------------------

fun AppCompatActivity.getViewPager2Adapter(): FrogoPagerHelper2 {
    return FrogoPagerHelper2(this.supportFragmentManager, this.lifecycle)
}

fun AppCompatActivity.preference(prefName: String): Lazy<FrogoPreference> {
    return lazy {
        FrogoPreference(this, prefName)
    }
}