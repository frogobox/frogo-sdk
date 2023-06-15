package com.frogobox.sdk.util

import androidx.fragment.app.Fragment


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

interface IFrogoPagerHelper {

    fun getTitles(): List<String>

    fun getFragments(): List<Fragment>

    fun addFragment(fragment: Fragment)

    fun addFragment(fragment: Fragment, title: String)

}