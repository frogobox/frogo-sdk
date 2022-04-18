package com.frogobox.sdk.ext

import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.frogobox.sdk.util.FrogoPagerHelper2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


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

// -------------------------------------------------------------------------------------------------

fun TextView.toText(): String {
    return this.text.toString()
}

// -------------------------------------------------------------------------------------------------

fun ViewPager2.getTitles(): List<String> {
    val viewPager2Adapter = this.adapter as FrogoPagerHelper2
    return viewPager2Adapter.getTitles()
}

// -------------------------------------------------------------------------------------------------

fun TabLayout.setupWithViewPager2(viewPager2: ViewPager2) {
    TabLayoutMediator(this, viewPager2) { tab, position ->
        tab.text = viewPager2.getTitles()[position]
    }.attach()
}

// -------------------------------------------------------------------------------------------------